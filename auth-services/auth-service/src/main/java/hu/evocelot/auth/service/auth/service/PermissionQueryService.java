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

import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryFilterParamsType;
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryOrderParamType;
import hu.evocelot.auth.common.system.jpa.service.AbstractQueryService;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.evocelot.auth.model.PermissionToSecurityGroup_;
import hu.evocelot.auth.model.Permission_;
import hu.evocelot.auth.model.SecurityGroup_;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Service class for listing {@link PermissionToSecurityGroup}s with filtering, sorting and paging.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionQueryService
        extends AbstractQueryService<PermissionToSecurityGroup, PermissionQueryFilterParamsType, PermissionQueryOrderParamType> {

    @Override
    protected Class<PermissionToSecurityGroup> getEntityClass() {
        return PermissionToSecurityGroup.class;
    }

    @Override
    protected void customFetching(Root<PermissionToSecurityGroup> root) {
        root.fetch(PermissionToSecurityGroup_.permission);
        root.fetch(PermissionToSecurityGroup_.securityGroup);
    }

    @Override
    protected List<Predicate> getFilterPredicates(CriteriaBuilder criteriaBuilder, Root<PermissionToSecurityGroup> root,
            PermissionQueryFilterParamsType permissionQueryFilterParamsType) throws BaseException {

        List<Predicate> predicates = new ArrayList<>();
        if (Objects.isNull(permissionQueryFilterParamsType)) {
            return predicates;
        }

        String nameFilterValue = permissionQueryFilterParamsType.getName();
        if (StringUtils.isNotBlank(nameFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(PermissionToSecurityGroup_.PERMISSION).get(Permission_.NAME),
                    likeParameter(nameFilterValue)));
        }

        String descriptionFilterValue = permissionQueryFilterParamsType.getDescription();
        if (StringUtils.isNotBlank(descriptionFilterValue)) {
            predicates.add(criteriaBuilder.like(root.get(PermissionToSecurityGroup_.PERMISSION).get(Permission_.DESCRIPTION),
                    likeParameter(nameFilterValue)));
        }

        String securityGroupIdFilterValue = permissionQueryFilterParamsType.getSecurityGroupId();
        if (StringUtils.isNotBlank(securityGroupIdFilterValue)) {
            predicates.add(criteriaBuilder.equal(root.get(PermissionToSecurityGroup_.SECURITY_GROUP).get(SecurityGroup_.ID),
                    securityGroupIdFilterValue));
        }

        return predicates;
    }

    @Override
    protected List<Order> getOrders(CriteriaBuilder criteriaBuilder, Root<PermissionToSecurityGroup> root,
            List<PermissionQueryOrderParamType> permissionQueryOrderParamTypes) throws BaseException {

        List<Order> orders = new ArrayList<>();
        if (Objects.isNull(permissionQueryOrderParamTypes)) {
            return orders;
        }

        for (PermissionQueryOrderParamType orderParam : permissionQueryOrderParamTypes) {
            switch (orderParam.getColumn()) {
            case NAME -> orders.add(createOrder(criteriaBuilder,
                    orderParam.getOrder(),
                    root.get(PermissionToSecurityGroup_.PERMISSION).get(Permission_.NAME)));
            case DESCRIPTION -> orders.add(createOrder(criteriaBuilder,
                    orderParam.getOrder(),
                    root.get(PermissionToSecurityGroup_.PERMISSION).get(Permission_.DESCRIPTION)));
            case SECURITY_GROUP_NAME -> orders.add(createOrder(criteriaBuilder,
                    orderParam.getOrder(),
                    root.get(PermissionToSecurityGroup_.SECURITY_GROUP).get(SecurityGroup_.NAME)));
            default -> throw new BusinessException(FaultType.QUERY_FAILED,
                    MessageFormat.format("Unsupported order column [{0}].", orderParam.getColumn()));
            }
        }

        return orders;
    }
}
