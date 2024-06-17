package hu.evocelot.auth.service.auth.action.securitygroup;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for updating security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateSecurityGroupAction extends BaseAction {
    public SecurityGroupResponse updateSecurityGroup(String securityGroupId, SecurityGroupRequest securityGroupRequest) throws BaseException {
        return null;
    }
}
