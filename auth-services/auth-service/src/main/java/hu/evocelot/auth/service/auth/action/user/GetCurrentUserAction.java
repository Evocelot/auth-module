package hu.evocelot.auth.service.auth.action.user;

import java.text.MessageFormat;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.auth._1_0.rest.auth.CurrentUserDetailsType;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.converter.token.TokenTypeConverter;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;
import hu.icellmobilsoft.coffee.tool.gson.JsonUtil;

/**
 * Action class for getting the current user based on the access token from the header.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetCurrentUserAction extends BaseAction {

    @Inject
    private TokenTypeConverter tokenTypeConverter;

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private RedisHelper redisHelper;

    @Inject
    private TokenService tokenService;

    /**
     * For getting the current user details from redis.
     *
     * @return - with {@link LoginResponse} that contains the current (logged) user details.
     * @throws BaseException
     *         - when an error occurs.
     */
    public LoginResponse getCurrentUser() throws BaseException {
        String accessTokenValue = projectHeader.getSessionToken();
        if (StringUtils.isBlank(accessTokenValue)) {
            throw new BusinessException(FaultType.ACCESS_TOKEN_NOT_PRESENT, "The access token not present in the header.");
        }

        Token accessToken = tokenService.findByValue(accessTokenValue, TokenType.ACCESS_TOKEN);

        Optional<String> redisResponse = redisHelper.getValue(accessTokenValue);
        if (redisResponse.isEmpty()) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessTokenValue));
        }

        CurrentUserDetailsType currentUserDetails = JsonUtil.toObject(redisResponse.get(), CurrentUserDetailsType.class);

        LoginResponse response = new LoginResponse();
        response.setUserDetails(currentUserDetails);
        response.setAccessToken(tokenTypeConverter.convert(accessToken));

        handleSuccessResultType(response);

        return response;
    }
}
