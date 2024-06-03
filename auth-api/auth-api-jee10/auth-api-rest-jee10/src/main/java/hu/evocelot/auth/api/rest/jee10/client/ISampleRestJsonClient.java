package hu.evocelot.auth.api.rest.jee10.client;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import hu.evocelot.auth.api.common.path.SamplePath;
import hu.evocelot.auth.api.rest.jee10.ISampleRest;

/**
 * Microprofile rest client implementation of {@link ISampleRest} for Json communication.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Path(SamplePath.REST_SAMPLE_SERVICE)
@RegisterRestClient
public interface ISampleRestJsonClient extends ISampleRest {

}
