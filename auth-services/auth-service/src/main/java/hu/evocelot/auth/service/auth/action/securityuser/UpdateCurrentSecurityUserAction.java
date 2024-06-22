package hu.evocelot.auth.service.auth.action.securityuser;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for updating the current (logged) security users.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateCurrentSecurityUserAction extends BaseAction {

    @Inject
    private UpdateSecurityUserAction updateSecurityUserAction;

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private TokenService tokenService;

    /**
     * For updating the current (logged) security user.
     *
     * @param updateSecurityUserRequest
     *         - the request that contains the information about the updated details of the security user.
     * @return - with {@link SecurityUserResponse} that contains the updated details of the security user.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityUserResponse updateCurrentSecurityUser(UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException {
        if (Objects.isNull(updateSecurityUserRequest)) {
            throw new InvalidParameterException("The updateSecurityUserRequest is null!");
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

        return updateSecurityUserAction.updateSecurityUser(accessTokenEntity.getSecurityUser().getId(), updateSecurityUserRequest);
    }
}
