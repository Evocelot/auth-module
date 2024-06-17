package hu.evocelot.auth.service.auth.action.permission;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Permission;
import hu.evocelot.auth.service.auth.converter.permission.PermissionEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PermissionService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for getting permissions.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetPermissionAction extends BaseAction {

    @Inject
    private PermissionEntityTypeConverter permissionEntityTypeConverter;

    @Inject
    private PermissionService permissionService;

    /**
     * For getting the permission based on the id.
     *
     * @param permissionId
     *         - the id of the permission.
     * @return - with {@link PermissionResponse} that contains the information about the permission.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PermissionResponse getPermission(String permissionId) throws BaseException {
        if (StringUtils.isBlank(permissionId)) {
            throw new InvalidParameterException("The permissionId is null!");
        }

        Permission permission = permissionService.findById(permissionId, Permission.class);

        PermissionResponse response = new PermissionResponse();
        response.setPermission(permissionEntityTypeConverter.convert(permission));
        handleSuccessResultType(response);

        return response;
    }
}
