package hu.evocelot.auth.service.auth.configuration;

import java.io.Serializable;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Configuration class for reading configuration values for auth service.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class AuthServiceConfiguration implements Serializable {

    /**
     * The max time-to-live value of the access tokens.
     */
    @Inject
    @ConfigProperty(name = "token.access.max-ttl")
    private int accessMaxTtl;

    /**
     * The max time-to-live value of the refresh tokens.
     */
    @Inject
    @ConfigProperty(name = "token.refresh.max-ttl")
    private int refreshMaxTtl;

    /**
     * For getting the accessMaxTtl.
     *
     * @return - with accessMaxTtl.
     */
    public int getAccessMaxTtl() {
        return accessMaxTtl;
    }

    /**
     * For getting the refreshMaxTtl.
     *
     * @return - with refreshMaxTtl.
     */
    public int getRefreshMaxTtl() {
        return refreshMaxTtl;
    }
}
