package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.rest.jee10.IUserRest;
import hu.evocelot.auth.api.user._1_0.rest.user.CreateUserRequest;
import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.user.CreateUserAction;
import hu.evocelot.auth.service.auth.action.user.DeleteUserAction;
import hu.evocelot.auth.service.auth.action.user.GetUserAction;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link IUserRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UserRest extends BaseRestService implements IUserRest {

    @Inject
    private CreateUserAction createUserAction;

    @Inject
    private GetUserAction getUserAction;

    @Inject
    private DeleteUserAction deleteUserAction;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
        return wrapPathParam1(createUserAction::createUser, createUserRequest, "createUser", "createUserRequest");
    }

    @Override
    public UserResponse getUser(String securityUserId) throws BaseException {
        return wrapPathParam1(getUserAction::getUser, securityUserId, "getUser", "securityUserId");
    }

    @Override
    public BaseResponse deleteUser(String securityUserId) throws BaseException {
        return wrapPathParam1(deleteUserAction::deleteUser, securityUserId, "deleteUser", "securityUserId");
    }
}
