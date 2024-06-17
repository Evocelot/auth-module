package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.rest.jee10.ISecurityGroupRest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.securitygroup.CreateSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.DeleteSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.GetSecurityGroupAction;
import hu.evocelot.auth.service.auth.action.securitygroup.UpdateSecurityGroupAction;
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

    @Override
    public SecurityGroupResponse createSecurityGroup(SecurityGroupRequest securityGroupRequest) throws BaseException {
        return wrapPathParam1(createSecurityGroupAction::createSecurityGroup, securityGroupRequest, "createSecurityGroup", "securityGroupRequest");
    }

    @Override
    public SecurityGroupResponse updateSecurityGroup(String securityGroupId, SecurityGroupRequest securityGroupRequest) throws BaseException {
        return wrapPathParam2(
                updateSecurityGroupAction::updateSecurityGroup,
                securityGroupId,
                securityGroupRequest,
                "updateSecurityGroup",
                "securityGroupId",
                "securityGroupRequest");
    }

    @Override
    public BaseResponse deleteSecurityGroup(String securityGroupId) throws BaseException {
        return wrapPathParam1(deleteSecurityGroupAction::deleteSecurityGroup, securityGroupId, "deleteSecurityGroup", "securityGroupId");
    }

    @Override
    public SecurityGroupResponse getSecurityGroup(String securityGroupId) throws BaseException {
        return wrapPathParam1(getSecurityGroupAction::getSecurityGroup, securityGroupId, "getSecurityGroup", "securityGroupId");
    }
}
