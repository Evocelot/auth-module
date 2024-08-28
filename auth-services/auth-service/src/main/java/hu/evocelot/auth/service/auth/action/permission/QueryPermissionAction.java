package hu.evocelot.auth.service.auth.action.permission;

import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.*;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Permission;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.evocelot.auth.service.auth.service.PermissionQueryService;
import hu.icellmobilsoft.coffee.dto.common.common.QueryRequestDetails;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.sql.paging.PagingResult;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Action class for listing {@link Permission}. In the background, this class works with {@link PermissionToSecurityGroup}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class QueryPermissionAction extends BaseAction {

    @Inject
    private PermissionQueryService permissionQueryService;

    /**
     * For listing {@link Permission} with filtering, sorting and paging.
     *
     * @param permissionQueryRequest - the request that contains information about the query.
     * @return - with {@link PermissionQueryResponse} that contains the relevant permissions.
     * @throws BaseException - when an error occurs.
     */
    public PermissionQueryResponse queryPermission(PermissionQueryRequest permissionQueryRequest) throws BaseException {
        if (Objects.isNull(permissionQueryRequest)) {
            throw new InvalidParameterException("The permissionQueryRequest is null!");
        }

        PermissionQueryFilterParamsType filterParams = permissionQueryRequest.getFilterParams();
        List<PermissionQueryOrderParamType> orderParams = permissionQueryRequest.getOrderParams();
        QueryRequestDetails pagingParams = permissionQueryRequest.getPagingDetails();

        PagingResult<Permission> pagingResult = permissionQueryService.findByQueryParams(filterParams, orderParams, pagingParams);

        PermissionQueryResponse response = new PermissionQueryResponse();
        response.setPagingDetails(permissionQueryService.getPagingDetails(pagingResult.getDetails()));
        response.withResults(convertResults(pagingResult.getResults()));
        handleSuccessResultType(response, permissionQueryRequest);

        return response;
    }

    private List<PermissionEntityQueryRowType> convertResults(List<Permission> results) throws BaseException {
        List<PermissionEntityQueryRowType> rows = new ArrayList<>();

        for (Permission result : results) {
            PermissionEntityQueryRowType row = new PermissionEntityQueryRowType();

            row.setPermissionId(result.getId());
            row.setName(result.getName());
            row.setDescription(result.getDescription());
            row.setSecurityGroupName(result.getName());

            rows.add(row);
        }

        return rows;
    }
}
