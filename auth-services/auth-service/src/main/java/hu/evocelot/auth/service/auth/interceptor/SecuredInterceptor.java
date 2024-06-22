package hu.evocelot.auth.service.auth.interceptor;

import java.text.MessageFormat;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.auth._1_0.rest.auth.CurrentUserDetailsType;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionEntityType;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.enums.CoffeeFaultType;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;
import hu.icellmobilsoft.coffee.tool.gson.JsonUtil;

/**
 * Resolver class for the {@link Secured} annotation.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Interceptor
@Secured
public class SecuredInterceptor {

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private RedisHelper redisHelper;

    @Inject
    private TokenService tokenService;

    /**
     * Reads the access token and validate the user based on the logged information. Checks that the user has the required permission too, to use the
     * called function.
     *
     * @param invocationContext
     *         - the invocation context.
     * @return - with the Object.
     * @throws Exception
     *         - when an error occurs.
     */
    @AroundInvoke
    public Object securedInvocation(InvocationContext invocationContext) throws Exception {
        // Get the access token from the header.
        String accessToken = projectHeader.getSessionToken();
        if (StringUtils.isBlank(accessToken)) {
            throw new BusinessException(FaultType.ACCESS_TOKEN_NOT_PRESENT, "The access token not present in the header.");
        }

        Token accessTokenEntity = null;
        try {
            accessTokenEntity = tokenService.findByValue(accessToken, TokenType.ACCESS_TOKEN);
        } catch (BONotFoundException e) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessToken));
        }

        if (accessTokenEntity.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessToken));
        }

        // Read the login details from redis by the session token.
        Optional<String> redisResponse = redisHelper.getValue(accessToken);
        if (redisResponse.isEmpty()) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessToken));
        }

        // Check the permission if needed.
        PermissionNeeded permissionNeededAnnotation = invocationContext.getMethod().getAnnotation(PermissionNeeded.class);
        if (Objects.nonNull(permissionNeededAnnotation)) {
            CurrentUserDetailsType currentUserDetailsType = JsonUtil.toObject(redisResponse.get(), CurrentUserDetailsType.class);
            String neededPermission = permissionNeededAnnotation.permission().name();
            if (!hasPermission(currentUserDetailsType.getSecurityGroup().getPermissions(), neededPermission)) {
                throw new BusinessException(CoffeeFaultType.NOT_AUTHORIZED, MessageFormat.format(
                        "The user with id [{0}] does not have the required permission to use this function!",
                        currentUserDetailsType.getUser().getSecurityUser().getSecurityUserId()));
            }
        }

        return invocationContext.proceed();
    }

    private boolean hasPermission(List<PermissionEntityType> userPermissions, String neededPermission) {
        for (PermissionEntityType permission : userPermissions) {
            if (permission.getPermissionId().equals(neededPermission)) {
                return true;
            }
        }

        return false;
    }
}
