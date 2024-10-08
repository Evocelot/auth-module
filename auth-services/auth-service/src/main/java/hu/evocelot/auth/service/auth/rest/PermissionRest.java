package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionRequest;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionResponse;
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryRequest;
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryResponse;
import hu.evocelot.auth.api.rest.jee10.IPartnerRest;
import hu.evocelot.auth.api.rest.jee10.IPermissionRest;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.permission.GetPermissionAction;
import hu.evocelot.auth.service.auth.action.permission.QueryPermissionAction;
import hu.evocelot.auth.service.auth.action.permission.UpdatePermissionAction;
import hu.evocelot.auth.service.auth.interceptor.Permission;
import hu.evocelot.auth.service.auth.interceptor.PermissionNeeded;
import hu.evocelot.auth.service.auth.interceptor.Secured;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link IPartnerRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionRest extends BaseRestService implements IPermissionRest {

    @Inject
    private GetPermissionAction getPermissionAction;

    @Inject
    private UpdatePermissionAction updatePermissionAction;

    @Inject
    private QueryPermissionAction queryPermissionAction;

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.GET_PERMISSION)
    public PermissionResponse getPermission(String permissionId) throws BaseException {
        return wrapPathParam1(getPermissionAction::getPermission, permissionId, "getPermission", "permissionId");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.UPDATE_PERMISSION)
    public PermissionResponse updatePermission(String permissionId, PermissionRequest permissionRequest) throws BaseException {
        return wrapPathParam2(updatePermissionAction::updatePermission,
                permissionId,
                permissionRequest,
                "updatePermission",
                "permissionId",
                "permissionRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.QUERY_PERMISSION)
    public PermissionQueryResponse queryPermission(PermissionQueryRequest permissionQueryRequest) throws BaseException {
        return wrapPathParam1(queryPermissionAction::queryPermission, permissionQueryRequest, "queryPermission", "permissionQueryRequest");
    }
}
