package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.common._1_0.common.EntityIdRequest;
import hu.evocelot.auth.api.rest.jee10.ISecurityGroupRest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryRequest;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.securitygroup.AddPermissionToSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.CreateSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.DeletePermissionFromSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.DeleteSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.GetSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.QuerySecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.UpdateSecurityGroupAction;
import hu.evocelot.auth.service.auth.interceptor.Permission;
import hu.evocelot.auth.service.auth.interceptor.PermissionNeeded;
import hu.evocelot.auth.service.auth.interceptor.Secured;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link ISecurityGroupRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupRest extends BaseRestService implements ISecurityGroupRest {

    @Inject
    private CreateSecurityGroupAction createSecurityGroupAction;

    @Inject
    private UpdateSecurityGroupAction updateSecurityGroupAction;

    @Inject
    private DeleteSecurityGroupAction deleteSecurityGroupAction;

    @Inject
    private GetSecurityGroupAction getSecurityGroupAction;

    @Inject
    private AddPermissionToSecurityGroupAction addPermissionToSecurityGroupAction;

    @Inject
    private DeletePermissionFromSecurityGroupAction deletePermissionFromSecurityGroupAction;

    @Inject
    private QuerySecurityGroupAction querySecurityGroupAction;

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.CREATE_SECURITY_GROUP)
    public SecurityGroupResponse createSecurityGroup(SecurityGroupRequest securityGroupRequest) throws BaseException {
        return wrapPathParam1(createSecurityGroupAction::createSecurityGroup, securityGroupRequest, "createSecurityGroup", "securityGroupRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.UPDATE_SECURITY_GROUP)
    public SecurityGroupResponse updateSecurityGroup(String securityGroupId, SecurityGroupRequest securityGroupRequest) throws BaseException {
        return wrapPathParam2(updateSecurityGroupAction::updateSecurityGroup,
                securityGroupId,
                securityGroupRequest,
                "updateSecurityGroup",
                "securityGroupId",
                "securityGroupRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.DELETE_SECURITY_GROUP)
    public BaseResponse deleteSecurityGroup(String securityGroupId) throws BaseException {
        return wrapPathParam1(deleteSecurityGroupAction::deleteSecurityGroup, securityGroupId, "deleteSecurityGroup", "securityGroupId");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.GET_SECURITY_GROUP)
    public SecurityGroupResponse getSecurityGroup(String securityGroupId) throws BaseException {
        return wrapPathParam1(getSecurityGroupAction::getSecurityGroup, securityGroupId, "getSecurityGroup", "securityGroupId");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.ADD_PERM_TO_SECURITY_GROUP)
    public BaseResponse addPermissionToSecurityGroup(String securityGroupId, EntityIdRequest entityIdRequest) throws BaseException {
        return wrapPathParam2(addPermissionToSecurityGroupAction::addPermissionToSecurityGroup,
                securityGroupId,
                entityIdRequest,
                "addPermissionToSecurityGroup",
                "securityGroupId",
                "entityIdRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.REMOVE_PERM_FROM_SEC_GROUP)
    public BaseResponse removePermissionFromSecurityGroup(String securityGroupId, EntityIdRequest entityIdRequest) throws BaseException {
        return wrapPathParam2(deletePermissionFromSecurityGroupAction::removePermissionFromSecurityGroup,
                securityGroupId,
                entityIdRequest,
                "removePermissionFromSecurityGroup",
                "securityGroupId",
                "entityIdRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.QUERY_SECURITY_GROUP)
    public SecurityGroupQueryResponse querySecurityGroup(SecurityGroupQueryRequest securityGroupQueryRequest) throws BaseException {
        return wrapPathParam1(querySecurityGroupAction::querySecurityGroup,
                securityGroupQueryRequest,
                "querySecurityGroup",
                "securityGroupQueryRequest");
    }
}
