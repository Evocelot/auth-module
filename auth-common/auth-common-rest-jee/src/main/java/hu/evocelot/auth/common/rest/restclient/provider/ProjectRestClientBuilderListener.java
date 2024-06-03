package hu.evocelot.auth.common.rest.restclient.provider;

import hu.icellmobilsoft.coffee.module.mp.restclient.provider.DefaultRestClientBuilderListener;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

/**
 * Project REST client listener.
 *
 * @author mark.danisovszky
 * @see DefaultRestClientBuilderListener
 * @since 0.1.0
 */
public class ProjectRestClientBuilderListener extends DefaultRestClientBuilderListener {

    @Override
    public void onNewBuilder(RestClientBuilder builder) {
        super.onNewBuilder(builder);

        builder.register(ProjectSettingClientRequestFilter.class);
    }
}
