package hu.evocelot.auth.api.rest.jee10.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleResponse;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.api.common.path.SamplePath;
import hu.evocelot.auth.api.common.restinformation.SampleRestInformation;
import hu.evocelot.auth.api.rest.jee10.ISampleRest;

/**
 * Microprofile rest client implementation of {@link ISampleRest} for XML communication.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Path(SamplePath.REST_SAMPLE_SERVICE)
@RegisterRestClient
public interface ISampleRestXmlClient extends ISampleRest {

    @Override
    @GET
    @Produces(value = { MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(SamplePath.ID)
    SampleResponse getSample(@PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SampleRestInformation.SAMPLE_PARAM_ID_DESCRIPTION, required = true) String sampleId) throws BaseException;

    @Override
    @POST
    @Produces(value = { MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    SampleResponse postSample(SampleRequest sampleRequest) throws BaseException;
}
