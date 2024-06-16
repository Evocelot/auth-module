package hu.evocelot.auth.service.auth.action.securityuser;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.user._1_0.rest.user.CreateUserRequest;
import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for getting security users.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetSecurityUserAction extends BaseAction {

    public SecurityUserResponse getSecurityUser(String securityUserId) throws BaseException {
        return null;
    }
}
