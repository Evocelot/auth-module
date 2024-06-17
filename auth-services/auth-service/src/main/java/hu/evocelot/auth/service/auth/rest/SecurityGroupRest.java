package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.rest.jee10.ISecurityGroupRest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
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

    @Override
    public SecurityGroupResponse createSecurityGroup(SecurityGroupRequest securityGroupRequest) throws BaseException {
        return null;
    }

    @Override
    public SecurityGroupResponse updateSecurityGroup(String securityGroupId, SecurityGroupRequest securityGroupRequest) throws BaseException {
        return null;
    }

    @Override
    public BaseResponse deleteSecurityGroup(String securityGroupId) throws BaseException {
        return null;
    }

    @Override
    public SecurityGroupResponse getSecurityGroup(String securityGroupId) throws BaseException {
        return null;
    }
}
