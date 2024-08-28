package hu.evocelot.auth.service.auth.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.*;
import hu.icellmobilsoft.coffee.dto.common.common.OrderByTypeType;
import hu.icellmobilsoft.coffee.dto.common.common.QueryRequestDetails;
import hu.icellmobilsoft.coffee.dto.common.common.QueryResponseDetails;
import hu.icellmobilsoft.coffee.dto.exception.TechnicalException;
import hu.icellmobilsoft.coffee.dto.exception.enums.CoffeeFaultType;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingResult;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingUtil;
import hu.icellmobilsoft.coffee.jpa.sql.paging.QueryMetaData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryFilterParamsType;
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryOrderParamType;
import hu.evocelot.auth.common.system.jpa.service.AbstractQueryService;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Service class for listing {@link PermissionToSecurityGroup}s with filtering, sorting and paging.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionQueryService extends BaseService<Permission> {

    public PagingResult<Permission> findByQueryParams(PermissionQueryFilterParamsType filterParams, List<PermissionQueryOrderParamType> orderParams, QueryRequestDetails pagingParams)
            throws BaseException {
        if (Objects.isNull(pagingParams)) {
            throw new BusinessException(CoffeeFaultType.WRONG_OR_MISSING_PARAMETERS, "The pagingParams is null!");
        }

        try {
            TypedQuery<Permission> query = buildQuery(filterParams, orderParams, getEntityClass());
            TypedQuery<Long> countQuery = buildCountQuery(filterParams);
            return PagingUtil.getPagingResult(query, countQuery, pagingParams.getPage(), pagingParams.getRows());
        } catch (BaseException e) {
            throw new TechnicalException(FaultType.QUERY_FAILED, MessageFormat.format("Query failed. Message: [{0}].", e.getMessage()), e);
        }
    }

    public TypedQuery<Permission> buildQuery(PermissionQueryFilterParamsType filterParams, List<PermissionQueryOrderParamType> orderParams, Class<PermissionToSecurityGroup> clazz) throws BaseException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Permission> criteriaQuery = criteriaBuilder.createQuery(Permission.class);
        Root<PermissionToSecurityGroup> root = criteriaQuery.from(PermissionToSecurityGroup.class);
        Join<PermissionToSecurityGroup, Permission> permissionJoin = root.join(PermissionToSecurityGroup_.PERMISSION);

        List<Predicate> predicates = getFilterPredicates(criteriaBuilder, root, filterParams);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        criteriaQuery.select(permissionJoin).distinct(true);

        List<Order> orders = getOrders(criteriaBuilder, root, orderParams);
        criteriaQuery.orderBy(orders);

        return getEntityManager().createQuery(criteriaQuery);
    }

    protected TypedQuery<Long> buildCountQuery(PermissionQueryFilterParamsType filterParams) throws BaseException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<PermissionToSecurityGroup> root = countQuery.from(PermissionToSecurityGroup.class);
        Join<PermissionToSecurityGroup, Permission> permissionJoin = root.join(PermissionToSecurityGroup_.PERMISSION);

        List<Predicate> predicates = getFilterPredicates(criteriaBuilder, root, filterParams);
        countQuery.where(predicates.toArray(new Predicate[0]));
        countQuery.select(criteriaBuilder.countDistinct(permissionJoin));

        return getEntityManager().createQuery(countQuery);
    }



    protected Class<PermissionToSecurityGroup> getEntityClass() {
        return PermissionToSecurityGroup.class;
    }

    protected void customFetching(Root<PermissionToSecurityGroup> root) {
        root.fetch(PermissionToSecurityGroup_.permission);
        root.fetch(PermissionToSecurityGroup_.securityGroup);
    }

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

    /**
     * Creates the order based on the orderType.
     *
     * @param criteriaBuilder
     *         - the criteria builder to use for creating order.
     * @param orderType
     *         - the type of the order (DESC, ASC).
     * @param attributePath
     *         - the path of the attribute to order.
     * @return - with the created {@link Order}.
     */
    protected Order createOrder(CriteriaBuilder criteriaBuilder, OrderByTypeType orderType, Path<?> attributePath) {
        return switch (orderType) {
            case ASC -> criteriaBuilder.asc(attributePath);
            case DESC -> criteriaBuilder.desc(attributePath);
        };
    }

    /**
     * Creates the {@link QueryRequestDetails} based on the {@link QueryMetaData}.
     *
     * @param metaData
     *         - the metadata of the query.
     * @return - with the created {@link QueryRequestDetails} that contains the paging details.
     */
    public QueryResponseDetails getPagingDetails(QueryMetaData metaData) {
        QueryResponseDetails details = new QueryResponseDetails();
        details.setPage(metaData.getPage().intValue());
        details.setMaxPage(metaData.getMaxPage().intValue());
        details.setRows(metaData.getRows().intValue());
        details.setTotalRows(metaData.getTotalRows().intValue());
        return details;
    }
}
