package hu.evocelot.ts.auth.common.base;

import java.net.URI;

import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import hu.icellmobilsoft.roaster.restassured.BaseConfigurableWeldIT;

/**
 * Base class for IT tests.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public abstract class BaseIT extends BaseConfigurableWeldIT {

    /**
     * Creates the specified rest client.
     *
     * @param restClientClass
     *         - the rest client to create.
     * @param restConfigKey
     *         - the rest config key for base path.
     * @param <T>
     *         - rest client type.
     * @return - with the created rest client.
     */
    protected <T> T getRestClient(Class<T> restClientClass, String restConfigKey) {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(ConfigProvider.getConfig().getValue(restConfigKey, String.class)))
                .build(restClientClass);
    }
}
