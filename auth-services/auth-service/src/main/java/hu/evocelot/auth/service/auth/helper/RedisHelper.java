package hu.evocelot.auth.service.auth.helper;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.service.auth.configuration.AuthServiceConfiguration;
import hu.icellmobilsoft.coffee.module.redis.annotation.RedisConnection;
import hu.icellmobilsoft.coffee.module.redis.manager.RedisManager;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
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

    public void storeTestData(String value) throws BaseException {
        redisManager.runWithConnection(Jedis::set, "set", "testKey", value);
    }

    public String getTestData() throws BaseException {
        Optional<String> optionalValue = redisManager.runWithConnection(Jedis::get, "get", "testKey");
        return optionalValue.get();
    }
}
