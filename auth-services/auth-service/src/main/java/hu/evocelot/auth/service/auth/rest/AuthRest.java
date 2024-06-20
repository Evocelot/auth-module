package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginRequest;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.api.rest.jee10.IAuthRest;
import hu.evocelot.auth.api.rest.jee10.IUserRest;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.auth.GetNewAccessTokenAction;
import hu.evocelot.auth.service.auth.action.auth.LoginAction;
import hu.evocelot.auth.service.auth.action.auth.LogoutAction;
import hu.evocelot.auth.service.auth.interceptor.Secured;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link IUserRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class AuthRest extends BaseRestService implements IAuthRest {

    @Inject
    private LoginAction loginAction;

    @Inject
    private LogoutAction logoutAction;

    @Inject
    private GetNewAccessTokenAction getNewAccessTokenAction;

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws BaseException {
        return wrapPathParam1(loginAction::login, loginRequest, "login", "loginRequest");
    }

    @Override
    @Secured
    public BaseResponse logout() throws BaseException {
        return wrapNoParam(logoutAction::logout, "logout");
    }

    @Override
    public LoginResponse getNewAccessToken(String refreshToken) throws BaseException {
        return wrapPathParam1(getNewAccessTokenAction::getNewAccessToken, refreshToken, "getNewAccessToken", "refreshToken");
    }
}
