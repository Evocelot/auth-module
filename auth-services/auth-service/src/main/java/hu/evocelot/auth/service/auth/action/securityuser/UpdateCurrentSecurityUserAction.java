package hu.evocelot.auth.service.auth.action.securityuser;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for updating the current (logged) security users.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateCurrentSecurityUserAction extends BaseAction {

    public SecurityUserResponse updateCurrentSecurityUser(UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException {
        return null;
    }
}
