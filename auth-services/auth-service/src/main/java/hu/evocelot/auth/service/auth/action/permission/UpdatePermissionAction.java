package hu.evocelot.auth.service.auth.action.permission;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionEntityCoreType;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionRequest;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Permission;
import hu.evocelot.auth.service.auth.converter.permission.PermissionEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.permission.PermissionEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PermissionService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for getting permissions.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdatePermissionAction extends BaseAction {

    @Inject
    private PermissionEntityCoreTypeConverter permissionEntityCoreTypeConverter;

    @Inject
    private PermissionEntityTypeConverter permissionEntityTypeConverter;

    @Inject
    private PermissionService permissionService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For updating the permissions base details.
     *
     * @param permissionId
     *         - the id of the permission to update.
     * @param permissionRequest
     *         - the request that contains information about the updated details.
     * @return - with {@link PermissionResponse} that contains the information about the updated details.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PermissionResponse updatePermission(String permissionId, PermissionRequest permissionRequest) throws BaseException {
        if (StringUtils.isBlank(permissionId)) {
            throw new InvalidParameterException("The permissionId is null!");
        }

        PermissionEntityCoreType updatedPermissionDetails = permissionRequest.getPermission();
        if (Objects.isNull(updatedPermissionDetails)) {
            throw new InvalidParameterException("The permissionRequest.getPermission is null!");
        }

        Permission permission = permissionService.findById(permissionId, Permission.class);

        String newPermissionName = updatedPermissionDetails.getName();
        if (!permission.getName().equals(newPermissionName)) {
            if (permissionService.nameAlreadyInUse(newPermissionName)) {
                throw new BusinessException(
                        FaultType.PERMISSION_NAME_ALREADY_IN_USE,
                        MessageFormat.format("The new name [{0}] of the permission is in use!", newPermissionName));
            }
        }

        permissionEntityCoreTypeConverter.convert(permission, updatedPermissionDetails);

        Permission finalEntity = permission;
        permission = transactionHelper.executeWithTransaction(() -> permissionService.save(finalEntity));

        PermissionResponse response = new PermissionResponse();
        response.setPermission(permissionEntityTypeConverter.convert(permission));
        handleSuccessResultType(response);

        return response;
    }
}
