package hu.evocelot.auth.service.auth.action.auth;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.auth._1_0.rest.auth.CurrentUserDetailsType;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginRequest;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.SecurityUserStatus;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.configuration.AuthServiceConfiguration;
import hu.evocelot.auth.service.auth.converter.user.UserTypeConverter;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.permission.PermissionEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.token.TokenTypeConverter;
import hu.evocelot.auth.service.auth.helper.PasswordHelper;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.helper.SecurityGroupHelper;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.auth.service.auth.service.PermissionToSecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.tool.utils.string.RandomUtil;

/**
 * Action class for logging in.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class LoginAction extends BaseAction {

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private PermissionEntityTypeConverter permissionEntityTypeConverter;

    @Inject
    private TokenTypeConverter tokenTypeConverter;

    @Inject
    private AuthServiceConfiguration authServiceConfiguration;

    @Inject
    private PasswordHelper passwordHelper;

    @Inject
    private TransactionHelper transactionHelper;

    @Inject
    private SecurityGroupHelper securityGroupHelper;

    @Inject
    private UserTypeConverter userTypeConverter;

    @Inject
    private RedisHelper redisHelper;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private TokenService tokenService;

    @Inject
    private PartnerService partnerService;

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private PermissionToSecurityGroupService permissionToSecurityGroupService;

    /**
     * For logging in. Creates a refresh and access token and stores in the redis and db too.
     *
     * @param loginRequest
     *         - the request that contains information about the login details.
     * @return - with {@link LoginRequest} that contains the details of the user and the tokens.
     * @throws BaseException
     *         - when an error occurs.
     */
    public LoginResponse login(LoginRequest loginRequest) throws BaseException {
        if (Objects.isNull(loginRequest)) {
            throw new InvalidParameterException("The loginRequest is null!");
        } else if (StringUtils.isBlank(loginRequest.getEmailAddress())) {
            throw new InvalidParameterException("The loginRequest.getEmailAddress is null!");
        } else if (StringUtils.isBlank(loginRequest.getPasswordHash())) {
            throw new InvalidParameterException("The loginRequest.getPasswordHash is null!");
        }

        SecurityUser securityUser;
        try {
            securityUser = securityUserService.findByEmailAddress(loginRequest.getEmailAddress());
        } catch (BONotFoundException e) {
            throw new BaseException(FaultType.LOGIN_FAILED, "Login failed!");
        }

        SecurityUserStatus status = securityUser.getStatus();
        switch (status) {
        case INACTIVE -> throw new BaseException(FaultType.SECURITY_USER_NOT_ACTIVATED,
                MessageFormat.format("The security user with id [{0}] is INACTIVE.", securityUser.getId()));
        case BANNED -> throw new BaseException(FaultType.SECURITY_USER_BANNED,
                MessageFormat.format("The security user with id [{0}] is BANNED.", securityUser.getId()));
        }

        String hashedAndSaltedPassword = passwordHelper.encryptPassword(loginRequest.getPasswordHash(), securityUser.getId());
        if (!securityUser.getPasswordHash().equals(hashedAndSaltedPassword)) {
            throw new BaseException(FaultType.LOGIN_FAILED, "Login failed!");
        }

        Token refreshToken = new Token();
        refreshToken.setToken(RandomUtil.generateId());
        refreshToken.setTokenType(TokenType.REFRESH_TOKEN);
        refreshToken.setSecurityUser(securityUser);
        refreshToken.setCreatedAt(OffsetDateTime.now());
        refreshToken.setExpiresAt(OffsetDateTime.now().plusMinutes(authServiceConfiguration.getRefreshMaxTtl()));

        Token accessToken = new Token();
        accessToken.setRelatedToken(refreshToken);
        accessToken.setToken(RandomUtil.generateId());
        accessToken.setTokenType(TokenType.ACCESS_TOKEN);
        accessToken.setSecurityUser(securityUser);
        accessToken.setCreatedAt(OffsetDateTime.now());
        accessToken.setExpiresAt(OffsetDateTime.now().plusMinutes(authServiceConfiguration.getAccessMaxTtl()));

        Token finalRefreshToken = refreshToken;
        refreshToken = transactionHelper.executeWithTransaction(() -> tokenService.save(finalRefreshToken));

        Token finalAccessToken = accessToken;
        accessToken = transactionHelper.executeWithTransaction(() -> tokenService.save(finalAccessToken));

        Partner partner = partnerService.findBySecurityUserId(securityUser.getId());

        SecurityGroup securityGroup = securityGroupService.findById(securityUser.getSecurityGroup().getId(), SecurityGroup.class);

        CurrentUserDetailsType currentUserDetailsType = new CurrentUserDetailsType();
        currentUserDetailsType.setRefreshToken(tokenTypeConverter.convert(refreshToken));
        currentUserDetailsType.setSecurityGroup(securityGroupHelper.getSecurityGroupWithPermissions(securityGroup));
        currentUserDetailsType.setUser(userTypeConverter.createUserType(securityUser, partner, securityGroup));

        redisHelper.storeLoginDetails(accessToken, currentUserDetailsType);

        LoginResponse response = new LoginResponse();

        response.setAccessToken(tokenTypeConverter.convert(accessToken));
        response.setUserDetails(currentUserDetailsType);

        handleSuccessResultType(response, loginRequest);

        return response;
    }

}
