package hu.evocelot.auth.service.auth.action.securitygroup;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for deleting security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class DeleteSecurityGroupAction extends BaseAction {
    public BaseResponse deleteSecurityGroup(String securityGroupId) throws BaseException {
        return null;
    }
}
