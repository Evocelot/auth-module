package hu.evocelot.auth.service.auth.action.auth;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for logging out.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class LogoutAction extends BaseAction {

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private TokenService tokenService;

    @Inject
    private TransactionHelper transactionHelper;

    @Inject
    private RedisHelper redisHelper;

    /**
     * For logging out.
     *
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public BaseResponse logout() throws BaseException {
        String accessTokenValue = projectHeader.getSessionToken();
        if (StringUtils.isBlank(accessTokenValue)) {
            throw new BaseException(FaultType.ACCESS_TOKEN_NOT_PRESENT, "The access token not present!");
        }

        Token accessToken = tokenService.findByValueFetchRelatedToken(accessTokenValue, TokenType.ACCESS_TOKEN);
        if (accessToken.getExpiresAt().isAfter(OffsetDateTime.now())) {
            accessToken.setExpiresAt(OffsetDateTime.now());
            transactionHelper.executeWithTransaction(() -> tokenService.save(accessToken));
        }

        Token refreshToken = accessToken.getRelatedToken();
        if (Objects.isNull(refreshToken) || StringUtils.isBlank(refreshToken.getToken())) {
            throw new BaseException(FaultType.REST_INTERNAL_SERVER_ERROR,
                    MessageFormat.format("Cannot find the related token for [{0}]", accessTokenValue));
        }

        refreshToken.setExpiresAt(OffsetDateTime.now());

        transactionHelper.executeWithTransaction(() -> tokenService.save(refreshToken));

        redisHelper.deleteKey(accessTokenValue);

        BaseResponse response = new BaseResponse();
        handleSuccessResultType(response);
        return response;
    }
}
