package hu.evocelot.auth.service.auth.action.auth;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.auth._1_0.rest.auth.CurrentUserDetailsType;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.configuration.AuthServiceConfiguration;
import hu.evocelot.auth.service.auth.converter.user.UserTypeConverter;
import hu.evocelot.auth.service.auth.converter.token.TokenTypeConverter;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.helper.SecurityGroupHelper;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;
import hu.icellmobilsoft.coffee.tool.utils.string.RandomUtil;

/**
 * Action class for logging getting the new access token.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetNewAccessTokenAction extends BaseAction {

    @Inject
    private TokenTypeConverter tokenTypeConverter;

    @Inject
    private RedisHelper redisHelper;

    @Inject
    private TransactionHelper transactionHelper;

    @Inject
    private SecurityGroupHelper securityGroupHelper;

    @Inject
    private UserTypeConverter userTypeConverter;

    @Inject
    private AuthServiceConfiguration authServiceConfiguration;

    @Inject
    private TokenService tokenService;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private PartnerService partnerService;

    @Inject
    private SecurityGroupService securityGroupService;

    /**
     * For creating a new access token and invalidates all the other tokens related to the refresh token.
     *
     * @param refreshToken
     *         - the value of the refresh token.
     * @return - with {@link LoginResponse} that contains the current user details.
     * @throws BaseException
     *         - when an error occurs.
     */
    public LoginResponse getNewAccessToken(String refreshToken) throws BaseException {
        if (StringUtils.isBlank(refreshToken)) {
            throw new InvalidParameterException("The refreshToken is blank!");
        }

        Token refreshTokenEntity = tokenService.findByValue(refreshToken, TokenType.REFRESH_TOKEN);
        if (refreshTokenEntity.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new BusinessException(FaultType.TOKEN_EXPIRED,
                    MessageFormat.format("The refresh token with value [{0}] is expired!", refreshToken));
        }

        Optional<Set<String>> optionalAccessTokenValues = redisHelper.getSMembers(refreshTokenEntity.getSecurityUser().getId());
        if (optionalAccessTokenValues.isPresent()) {
            Set<String> accessTokenValues = optionalAccessTokenValues.get();
            for (String accessTokenValue : accessTokenValues) {
                redisHelper.deleteKey(accessTokenValue);
                // TODO: Set expiration time in db too.
            }
        }

        SecurityUser securityUser = securityUserService.findById(refreshTokenEntity.getSecurityUser().getId(), SecurityUser.class);
        SecurityGroup securityGroup = securityGroupService.findById(securityUser.getSecurityGroup().getId(), SecurityGroup.class);

        Token newAccessToken = new Token();
        newAccessToken.setRelatedToken(refreshTokenEntity);
        newAccessToken.setToken(RandomUtil.generateId());
        newAccessToken.setTokenType(TokenType.ACCESS_TOKEN);
        newAccessToken.setSecurityUser(securityUser);
        newAccessToken.setCreatedAt(OffsetDateTime.now());
        newAccessToken.setExpiresAt(OffsetDateTime.now().plusMinutes(authServiceConfiguration.getAccessMaxTtl()));

        Token finalNewAccessToken = newAccessToken;
        newAccessToken = transactionHelper.executeWithTransaction(() -> tokenService.save(finalNewAccessToken));

        Partner partner = partnerService.findBySecurityUserId(securityUser.getId());

        CurrentUserDetailsType currentUserDetailsType = new CurrentUserDetailsType();
        currentUserDetailsType.setRefreshToken(tokenTypeConverter.convert(refreshTokenEntity));
        currentUserDetailsType.setSecurityGroup(securityGroupHelper.getSecurityGroupWithPermissions(securityGroup));
        currentUserDetailsType.setUser(userTypeConverter.createUserType(securityUser, partner, securityGroup));

        redisHelper.storeLoginDetails(newAccessToken, currentUserDetailsType);

        LoginResponse response = new LoginResponse();

        response.setAccessToken(tokenTypeConverter.convert(newAccessToken));
        response.setUserDetails(currentUserDetailsType);

        handleSuccessResultType(response);

        return response;
    }
}
