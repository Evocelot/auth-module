package hu.evocelot.auth.service.auth.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserStatusEnumType;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryFilterParamsType;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryOrderParamType;
import hu.evocelot.auth.common.system.jpa.service.AbstractQueryService;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.Partner_;
import hu.evocelot.auth.model.SecurityGroup_;
import hu.evocelot.auth.model.SecurityUser_;
import hu.evocelot.auth.model.enums.SecurityUserStatus;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;
import hu.icellmobilsoft.coffee.tool.utils.enums.EnumUtil;

/**
 * Service class for listing {@link Partner}s with filtering, sorting and paging.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UserQueryService extends AbstractQueryService<Partner, UserQueryFilterParamsType, UserQueryOrderParamType> {

    @Override
    protected Class<Partner> getEntityClass() {
        return Partner.class;
    }

    @Override
    protected void customFetching(Root<Partner> root) {
        root.fetch(Partner_.SECURITY_USER);
        root.fetch(Partner_.SECURITY_USER).fetch(SecurityUser_.SECURITY_GROUP);
    }

    @Override
    protected List<Predicate> getFilterPredicates(CriteriaBuilder criteriaBuilder, Root<Partner> root,
            UserQueryFilterParamsType userQueryFilterParamsType) throws BaseException {

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.isNull(userQueryFilterParamsType)) {
            return predicates;
        }

        String nameFilterValue = userQueryFilterParamsType.getName();
        if (StringUtils.isNotBlank(nameFilterValue)) {
            String[] filterValues = nameFilterValue.split("\\s+");
            List<Predicate> namePredicates = new ArrayList<>();

            for (String value : filterValues) {
                String likePattern = likeParameter(value);
                Predicate firstNamePredicate = criteriaBuilder.like(root.get(Partner_.FIRST_NAME), likePattern);
                Predicate lastNamePredicate = criteriaBuilder.like(root.get(Partner_.LAST_NAME), likePattern);
                Predicate orPredicate = criteriaBuilder.or(firstNamePredicate, lastNamePredicate);
                namePredicates.add(orPredicate);
            }

            predicates.add(criteriaBuilder.and(namePredicates.toArray(new Predicate[0])));
        }

        String phoneNumberFilterValue = userQueryFilterParamsType.getPhoneNumber();
        if (StringUtils.isNotBlank(phoneNumberFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(Partner_.PHONE_NUMBER), likeParameter(phoneNumberFilterValue)));
        }

        String emailAddressFilterValue = userQueryFilterParamsType.getEmailAddress();
        if (StringUtils.isNotBlank(emailAddressFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(Partner_.SECURITY_USER).get(SecurityUser_.EMAIL_ADDRESS),
                    likeParameter(emailAddressFilterValue)));
        }

        String securityGroupIdFilterValue = userQueryFilterParamsType.getSecurityGroupId();
        if (StringUtils.isNotBlank(securityGroupIdFilterValue)) {
            predicates.add(criteriaBuilder.equal(root.get(Partner_.SECURITY_USER).get(SecurityUser_.SECURITY_GROUP).get(SecurityGroup_.ID),
                    securityGroupIdFilterValue));
        }

        SecurityUserStatusEnumType statusFilterValue = userQueryFilterParamsType.getStatus();
        if (Objects.nonNull(statusFilterValue)) {
            predicates.add(criteriaBuilder.equal(root.get(Partner_.SECURITY_USER).get(SecurityUser_.STATUS),
                    EnumUtil.convert(statusFilterValue, SecurityUserStatus.class)));
        }

        return predicates;
    }

    @Override
    protected List<Order> getOrders(CriteriaBuilder criteriaBuilder, Root<Partner> root, List<UserQueryOrderParamType> userQueryOrderParamTypes)
            throws BaseException {

        List<Order> orders = new ArrayList<>();
        if (Objects.isNull(userQueryOrderParamTypes)) {
            return orders;
        }

        for (UserQueryOrderParamType orderParam : userQueryOrderParamTypes) {
            switch (orderParam.getColumn()) {
            case EMAIL_ADDRESS -> orders.add(createOrder(criteriaBuilder,
                    orderParam.getOrder(),
                    root.get(Partner_.SECURITY_USER).get(SecurityUser_.EMAIL_ADDRESS)));
            case SECURITY_GROUP_NAME -> orders.add(createOrder(criteriaBuilder,
                    orderParam.getOrder(),
                    root.get(Partner_.SECURITY_USER).get(SecurityUser_.SECURITY_GROUP).get(SecurityGroup_.ID)));
            case STATUS ->
                    orders.add(createOrder(criteriaBuilder, orderParam.getOrder(), root.get(Partner_.SECURITY_USER).get(SecurityUser_.STATUS)));
            case NAME -> {
                orders.add(createOrder(criteriaBuilder, orderParam.getOrder(), root.get(Partner_.FIRST_NAME)));
                orders.add(createOrder(criteriaBuilder, orderParam.getOrder(), root.get(Partner_.LAST_NAME)));
            }
            default -> throw new BusinessException(FaultType.QUERY_FAILED,
                    MessageFormat.format("Unsupported order column [{0}].", orderParam.getColumn()));
            }
        }

        return orders;
    }
}
