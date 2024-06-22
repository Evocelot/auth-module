package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.rest.jee10.ISecurityUserRest;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.ChangeCurrentPasswordRequest;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.securityuser.ChangeCurrentPasswordAction;
import hu.evocelot.auth.service.auth.action.securityuser.UpdateCurrentSecurityUserAction;
import hu.evocelot.auth.service.auth.action.securityuser.UpdateSecurityUserAction;
import hu.evocelot.auth.service.auth.interceptor.Permission;
import hu.evocelot.auth.service.auth.interceptor.PermissionNeeded;
import hu.evocelot.auth.service.auth.interceptor.Secured;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link ISecurityUserRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityUserRest extends BaseRestService implements ISecurityUserRest {

    @Inject
    private UpdateSecurityUserAction updateSecurityUserAction;

    @Inject
    private UpdateCurrentSecurityUserAction updateCurrentSecurityUserAction;

    @Inject
    private ChangeCurrentPasswordAction changeCurrentPasswordAction;

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.UPDATE_SECURITY_USER)
    public SecurityUserResponse updateSecurityUser(String securityUserId, UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException {
        return wrapPathParam2(
                updateSecurityUserAction::updateSecurityUser,
                securityUserId,
                updateSecurityUserRequest,
                "updateSecurityUser",
                "securityUserId",
                "updateSecurityUserRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.UPDATE_CURRENT_SECURITY_USER)
    public SecurityUserResponse updateCurrentSecurityUser(UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException {
        return wrapPathParam1(
                updateCurrentSecurityUserAction::updateCurrentSecurityUser,
                updateSecurityUserRequest,
                "updateCurrentSecurityUser",
                "updateSecurityUserRequest");
    }

    @Override
    @Secured
    @PermissionNeeded(permission = Permission.CHANGE_CURRENT_PASSWORD)
    public BaseResponse changeCurrentPassword(ChangeCurrentPasswordRequest changeCurrentPasswordRequest) throws BaseException {
        return wrapPathParam1(
                changeCurrentPasswordAction::changeCurrentPassword,
                changeCurrentPasswordRequest,
                "changeCurrentPassword",
                "changeCurrentPasswordRequest");
    }
}
