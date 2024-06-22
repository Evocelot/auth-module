package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.api.rest.jee10.IUserRest;
import hu.evocelot.auth.api.user._1_0.rest.user.CreateUserRequest;
import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryRequest;
import hu.evocelot.auth.api.userquery._1_0.rest.user_query.UserQueryResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.user.CreateUserAction;
import hu.evocelot.auth.service.auth.action.user.DeleteUserAction;
import hu.evocelot.auth.service.auth.action.user.GetCurrentUserAction;
import hu.evocelot.auth.service.auth.action.user.GetUserAction;
import hu.evocelot.auth.service.auth.action.user.QueryUserAction;
import hu.evocelot.auth.service.auth.interceptor.Permission;
import hu.evocelot.auth.service.auth.interceptor.PermissionNeeded;
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
public class UserRest extends BaseRestService implements IUserRest {

    @Inject
    private CreateUserAction createUserAction;

    @Inject
    private GetUserAction getUserAction;

    @Inject
    private DeleteUserAction deleteUserAction;

    @Inject
    private QueryUserAction queryUserAction;

    @Inject
    private GetCurrentUserAction getCurrentUserAction;

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.CREATE_USER)
    public UserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
        return wrapPathParam1(createUserAction::createUser, createUserRequest, "createUser", "createUserRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.GET_USER)
    public UserResponse getUser(String securityUserId) throws BaseException {
        return wrapPathParam1(getUserAction::getUser, securityUserId, "getUser", "securityUserId");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.DELETE_USER)
    public BaseResponse deleteUser(String securityUserId) throws BaseException {
        return wrapPathParam1(deleteUserAction::deleteUser, securityUserId, "deleteUser", "securityUserId");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.QUERY_USER)
    public UserQueryResponse queryUser(UserQueryRequest userQueryRequest) throws BaseException {
        return wrapPathParam1(queryUserAction::queryUser, userQueryRequest, "queryUser", "userQueryRequest");
    }

    @Override
    @Secured
    public LoginResponse getCurrentUser() throws BaseException {
        return wrapNoParam(getCurrentUserAction::getCurrentUser, "getCurrentUser");
    }
}
