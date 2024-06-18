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

import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryFilterParamsType;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryOrderParamType;
import hu.evocelot.auth.common.system.jpa.service.AbstractQueryService;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityGroup_;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Service class for listing {@link SecurityGroup}s with filtering, sorting and paging.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupQueryService
        extends AbstractQueryService<SecurityGroup, SecurityGroupQueryFilterParamsType, SecurityGroupQueryOrderParamType> {

    @Override
    protected Class<SecurityGroup> getEntityClass() {
        return SecurityGroup.class;
    }

    @Override
    protected List<Predicate> getFilterPredicates(CriteriaBuilder criteriaBuilder, Root<SecurityGroup> root,
            SecurityGroupQueryFilterParamsType securityGroupQueryFilterParamsType) throws BaseException {

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.isNull(securityGroupQueryFilterParamsType)) {
            return predicates;
        }

        String nameFilterValue = securityGroupQueryFilterParamsType.getName();
        if (StringUtils.isNotBlank(nameFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(SecurityGroup_.NAME), likeParameter(nameFilterValue)));
        }

        String descriptionFilterValue = securityGroupQueryFilterParamsType.getDescription();
        if (StringUtils.isNotBlank(descriptionFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(SecurityGroup_.DESCRIPTION), likeParameter(descriptionFilterValue)));
        }

        return predicates;
    }

    @Override
    protected List<Order> getOrders(CriteriaBuilder criteriaBuilder, Root<SecurityGroup> root,
            List<SecurityGroupQueryOrderParamType> securityGroupQueryOrderParamTypes) throws BaseException {

        List<Order> orders = new ArrayList<>();
        if (Objects.isNull(securityGroupQueryOrderParamTypes)) {
            return orders;
        }

        for (SecurityGroupQueryOrderParamType orderParam : securityGroupQueryOrderParamTypes) {
            switch (orderParam.getColumn()) {
            case NAME -> orders.add(createOrder(criteriaBuilder, orderParam.getOrder(), root.get(SecurityGroup_.NAME)));
            case DESCRIPTION -> orders.add(createOrder(criteriaBuilder, orderParam.getOrder(), root.get(SecurityGroup_.DESCRIPTION)));
            default -> throw new BusinessException(FaultType.QUERY_FAILED,
                    MessageFormat.format("Unsupported order column [{0}].", orderParam.getColumn()));
            }
        }

        return orders;
    }
}
