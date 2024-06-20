package hu.evocelot.auth.service.auth.helper;

import java.util.Optional;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.auth._1_0.rest.auth.CurrentUserDetailsType;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.service.auth.configuration.AuthServiceConfiguration;
import hu.icellmobilsoft.coffee.module.redis.annotation.RedisConnection;
import hu.icellmobilsoft.coffee.module.redis.manager.RedisManager;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.tool.gson.JsonUtil;
import redis.clients.jedis.Jedis;

/**
 * Helper class for handling redis operations.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class RedisHelper {

    private static final String REDIS_CONFIG_KEY = "auth";

    @Inject
    @RedisConnection(configKey = REDIS_CONFIG_KEY)
    private RedisManager redisManager;

    @Inject
    private AuthServiceConfiguration authServiceConfiguration;

    /**
     * Stores the login details into the redis. accessTokenValue -> currentUserDetails (expires) securityUserId -> accessToken (expires)
     * securityGroupId -> securityUserId
     *
     * @param accessToken
     *         - the access token.
     * @param currentUserDetailsType
     *         - the current user details.
     * @throws BaseException
     *         - when an error occurs.
     */
    public void storeLoginDetails(Token accessToken, CurrentUserDetailsType currentUserDetailsType) throws BaseException {
        String accessTokenValue = accessToken.getToken();
        redisManager.runWithConnection(Jedis::set, "set", accessTokenValue, JsonUtil.toJson(currentUserDetailsType));
        redisManager.runWithConnection(Jedis::expire, "expire", accessTokenValue, authServiceConfiguration.getAccessMaxTtl() * 60);

        String securityUserId = currentUserDetailsType.getUser().getSecurityUser().getSecurityUserId();
        redisManager.runWithConnection(Jedis::sadd, "sadd", securityUserId, accessTokenValue);
        redisManager.runWithConnection(Jedis::expire, "expire", securityUserId, authServiceConfiguration.getRefreshMaxTtl() * 60);

        String securityGroupId = currentUserDetailsType.getSecurityGroup().getSecurityGroupId();
        redisManager.runWithConnection(Jedis::sadd, "sadd", securityGroupId, securityUserId);
        redisManager.runWithConnection(Jedis::expire, "expire", securityGroupId, authServiceConfiguration.getRefreshMaxTtl() * 60);
    }

    /**
     * Deletes all the sessions (access token keys) to the security user. The other data in redis to the user will be deleted when the keys are
     * expired.
     *
     * @param securityUserId
     *         - the id of the security user.
     * @throws BaseException
     *         - when an error occurs.
     */
    public void endUserSessions(String securityUserId) throws BaseException {
        Optional<Set<String>> optionalAccessTokenValues = redisManager.runWithConnection(Jedis::smembers, "smembers", securityUserId);
        if (optionalAccessTokenValues.isPresent()) {
            Set<String> accessTokenValues = optionalAccessTokenValues.get();
            for (String accessTokenValue : accessTokenValues) {
                deleteKey(accessTokenValue);
            }
        }
    }

    /**
     * Deletes all the sessions that used in a specified security group.
     *
     * @param securityGroupId
     *         - the id of the security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    public void endSecurityGroupSessions(String securityGroupId) throws BaseException {
        Optional<Set<String>> securityUserIds = redisManager.runWithConnection(Jedis::smembers, "smembers", securityGroupId);
        if (securityUserIds.isPresent()) {
            for (String securityUserId : securityUserIds.get()) {
                endUserSessions(securityUserId);
            }
        }
    }

    /**
     * Deletes the key and the connected value.
     *
     * @param key
     *         - the key to delete.
     * @throws BaseException
     *         - when error occurs.
     */
    public void deleteKey(String key) throws BaseException {
        redisManager.runWithConnection(Jedis::del, "del", key);
    }

    /**
     * Reads the value of the key.
     *
     * @param key
     *         - the key to read.
     * @return - with the value of the key.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Optional<String> getValue(String key) throws BaseException {
        return redisManager.runWithConnection(Jedis::get, "get", key);
    }

    /**
     * Reads the value of the key.
     *
     * @param key
     *         - the key.
     * @return - with the value of the key.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Optional<java.util.Set<String>> getSMembers(String key) throws BaseException {
        return redisManager.runWithConnection(Jedis::smembers, "smembers", key);
    }
}
