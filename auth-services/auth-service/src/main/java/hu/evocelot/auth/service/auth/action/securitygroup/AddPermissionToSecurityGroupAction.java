package hu.evocelot.auth.service.auth.action.securitygroup;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.common._1_0.common.EntityIdRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Permission;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.service.PermissionService;
import hu.evocelot.auth.service.auth.service.PermissionToSecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for adding permission to the security group.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class AddPermissionToSecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private PermissionService permissionService;

    @Inject
    private PermissionToSecurityGroupService permissionToSecurityGroupService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For adding permission into the security group.
     *
     * @param securityGroupId
     *         - the id of the security group to add the permission.
     * @param entityIdRequest
     *         - the request that contains the id of the permission to add.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public BaseResponse addPermissionToSecurityGroup(String securityGroupId, EntityIdRequest entityIdRequest) throws BaseException {
        if (StringUtils.isBlank(securityGroupId)) {
            throw new InvalidParameterException("The securityGroupId is blank!");
        } else if (Objects.isNull(entityIdRequest)) {
            throw new InvalidParameterException("The entityIdRequest is null!");
        }

        String permissionId = entityIdRequest.getEntityId();
        if (StringUtils.isBlank(permissionId)) {
            throw new InvalidParameterException("The permissionId is blank!");
        }

        SecurityGroup securityGroup = securityGroupService.findById(securityGroupId, SecurityGroup.class);
        Permission permission = permissionService.findById(permissionId, Permission.class);

        if (!permissionToSecurityGroupService.hasPermission(securityGroup.getId(), permission.getId())) {
            PermissionToSecurityGroup permissionToSecurityGroup = new PermissionToSecurityGroup();
            permissionToSecurityGroup.setPermission(permission);
            permissionToSecurityGroup.setSecurityGroup(securityGroup);
            transactionHelper.executeWithTransaction(() -> permissionToSecurityGroupService.save(permissionToSecurityGroup));
        }

        BaseResponse response = new BaseResponse();
        handleSuccessResultType(response);

        return response;
    }
}
