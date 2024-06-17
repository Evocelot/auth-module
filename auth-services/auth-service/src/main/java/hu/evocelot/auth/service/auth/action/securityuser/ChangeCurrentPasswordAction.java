package hu.evocelot.auth.service.auth.action.securityuser;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.ChangeCurrentPasswordRequest;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for change the current security user (logged) action.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class ChangeCurrentPasswordAction extends BaseAction {

    public SecurityUserResponse changeCurrentPassword(ChangeCurrentPasswordRequest changeCurrentPasswordRequest) throws BaseException {
        return null;
    }
}
