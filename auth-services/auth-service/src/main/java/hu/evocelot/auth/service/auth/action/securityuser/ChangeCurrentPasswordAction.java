package hu.evocelot.auth.service.auth.action.securityuser;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.ChangeCurrentPasswordRequest;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.helper.PasswordHelper;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for change the current security user (logged) action.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class ChangeCurrentPasswordAction extends BaseAction {

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private TokenService tokenService;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private PasswordHelper passwordHelper;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For updating the current (logged) user password.
     *
     * @param changeCurrentPasswordRequest
     *         - the request that contains the current and the updated password hash.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */

    public BaseResponse changeCurrentPassword(ChangeCurrentPasswordRequest changeCurrentPasswordRequest) throws BaseException {
        if (Objects.isNull(changeCurrentPasswordRequest)) {
            throw new InvalidParameterException("The changeCurrentPasswordRequest is null!");
        }

        String accessTokenValue = projectHeader.getSessionToken();
        if (StringUtils.isBlank(accessTokenValue)) {
            throw new BusinessException(FaultType.ACCESS_TOKEN_NOT_PRESENT, "The access token not present in the header.");
        }

        Token accessTokenEntity;
        try {
            accessTokenEntity = tokenService.findByValue(accessTokenValue, TokenType.ACCESS_TOKEN);
        } catch (BONotFoundException e) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessTokenValue));
        }

        SecurityUser securityUser = securityUserService.findById(accessTokenEntity.getSecurityUser().getId(), SecurityUser.class);

        String hashedSaltedOriginalPassword = passwordHelper.encryptPassword(changeCurrentPasswordRequest.getActualPasswordHash(),
                securityUser.getId());
        if (!hashedSaltedOriginalPassword.equals(securityUser.getPasswordHash())) {
            throw new BaseException(FaultType.INVALID_CREDENTIALS, "Invalid credentials!");
        }

        String hashedSaltedNewPassword = passwordHelper.encryptPassword(changeCurrentPasswordRequest.getNewPasswordHash(), securityUser.getId());
        securityUser.setPasswordHash(hashedSaltedNewPassword);

        transactionHelper.executeWithTransaction(() -> securityUserService.save(securityUser));

        BaseResponse response = new BaseResponse();
        handleSuccessResultType(response);

        return response;
    }
}
