package hu.evocelot.auth.common.system.jpa.service;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.icellmobilsoft.coffee.dto.common.common.OrderByTypeType;
import hu.icellmobilsoft.coffee.dto.common.common.QueryRequestDetails;
import hu.icellmobilsoft.coffee.dto.common.common.QueryResponseDetails;
import hu.icellmobilsoft.coffee.dto.exception.TechnicalException;
import hu.icellmobilsoft.coffee.dto.exception.enums.CoffeeFaultType;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingResult;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingUtil;
import hu.icellmobilsoft.coffee.jpa.sql.paging.QueryMetaData;
import hu.icellmobilsoft.coffee.model.base.AbstractIdentifiedAuditEntity_;
import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * A common query service class for handling queries with paging, ordering, and filtering capabilities.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public abstract class AbstractQueryService<ENTITY extends AbstractIdentifiedAuditEntity, FILTER_PARAMS, ORDER_PARAM> extends BaseService<ENTITY>
        implements Serializable {

    /**
     * Finds a list of {@link ENTITY} based on filtering, sorting, and paging details, and creates a {@link PagingResult}.
     *
     * @param filterParams
     *         - the parameters for filtering the result set.
     * @param orderParams
     *         - the parameters for sorting the result set.
     * @param pagingParams
     *         - the parameters for paging the results.
     * @return - with a {@link PagingResult<ENTITY>} containing the filtered, sorted, and paged results.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PagingResult<ENTITY> findByQueryParams(FILTER_PARAMS filterParams, List<ORDER_PARAM> orderParams, QueryRequestDetails pagingParams)
            throws BaseException {
        if (Objects.isNull(pagingParams)) {
            throw new BusinessException(CoffeeFaultType.WRONG_OR_MISSING_PARAMETERS, "The pagingParams is null!");
        }

        try {
            TypedQuery<ENTITY> query = buildQuery(filterParams, orderParams, getEntityClass());
            TypedQuery<Long> countQuery = buildCountQuery(filterParams);
            return PagingUtil.getPagingResult(query, countQuery, pagingParams.getPage(), pagingParams.getRows());
        } catch (BaseException e) {
            throw new TechnicalException(FaultType.QUERY_FAILED, MessageFormat.format("Query failed. Message: [{0}].", e.getMessage()), e);
        }
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

    /**
     * Defines the entity class.
     *
     * @return - with the class of the managed entity.
     */
    protected abstract Class<ENTITY> getEntityClass();

    /**
     * Defines all the predicates for filtering the result set.
     *
     * @param criteriaBuilder
     *         - the criteria builder to use for creating predicates.
     * @param root
     *         - the root path of the entity.
     * @param filterParams
     *         - the filter params from the request.
     * @return - with a list of {@link Predicate}.
     * @throws BaseException
     *         - when an error occurs.
     */
    protected abstract List<Predicate> getFilterPredicates(CriteriaBuilder criteriaBuilder, Root<ENTITY> root, FILTER_PARAMS filterParams)
            throws BaseException;

    /**
     * Defines all the orders for sorting the result set.
     *
     * @param criteriaBuilder
     *         - the criteria builder to use for creating orders.
     * @param root
     *         - the root path of the entity.
     * @param orderParams
     *         - the order parameters from the request.
     * @return - with a list of {@link Order}.
     * @throws BaseException
     *         - when an error occurs.
     */
    protected abstract List<Order> getOrders(CriteriaBuilder criteriaBuilder, Root<ENTITY> root, List<ORDER_PARAM> orderParams) throws BaseException;

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
     * Method for defining the default sorting options for the result set. You can add the default orders into the {@param orders}.
     *
     * @param criteriaBuilder
     *         - the criteria builder to use for creating predicates.
     * @param root
     *         - the root path of the entity.
     * @param orders
     *         - the list of {@link Order}s to store the default order.
     */
    protected void processDefaultOrder(CriteriaBuilder criteriaBuilder, Root<ENTITY> root, List<Order> orders) {
        orders.add(createOrder(criteriaBuilder, OrderByTypeType.ASC, root.get(AbstractIdentifiedAuditEntity_.CREATION_DATE)));
        orders.add(createOrder(criteriaBuilder, OrderByTypeType.ASC, root.get(AbstractIdentifiedAuditEntity_.ID)));
    }

    /**
     * Hook method for optional fetching sub entities into the {@link ENTITY}.
     *
     * @param root
     *         - the root path of the entity.
     */
    protected void customFetching(Root<ENTITY> root) {

    }

    protected TypedQuery<ENTITY> buildQuery(FILTER_PARAMS filterParams, List<ORDER_PARAM> orderParams, Class<ENTITY> clazz) throws BaseException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ENTITY> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<ENTITY> root = criteriaQuery.from(getEntityClass());

        customFetching(root);

        criteriaQuery.select(root).distinct(true);

        List<Order> orders = getOrders(criteriaBuilder, root, orderParams);
        processDefaultOrder(criteriaBuilder, root, orders);

        criteriaQuery.orderBy(orders);

        List<Predicate> predicates = getFilterPredicates(criteriaBuilder, root, filterParams);
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        return getEntityManager().createQuery(criteriaQuery);
    }

    private TypedQuery<Long> buildCountQuery(FILTER_PARAMS filterParams) throws BaseException {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class).distinct(true);
        Root<ENTITY> root = criteriaQuery.from(getEntityClass());

        criteriaQuery.select(criteriaBuilder.count(root));

        List<Predicate> predicates = getFilterPredicates(criteriaBuilder, root, filterParams);
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        return getEntityManager().createQuery(criteriaQuery);
    }
}
