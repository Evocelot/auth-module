package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleResponse;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.api.common.path.SamplePath;
import hu.evocelot.auth.api.common.restinformation.SampleRestInformation;

/**
 * REST endpoint for sample-jpa-service.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Tag(name = SampleRestInformation.TAG, description = SampleRestInformation.DESCRIPTION)
@Path(SamplePath.REST_SAMPLE_SERVICE)
public interface ISampleRest {

    /**
     * Sample http GET method for reading sample entity.
     *
     * @param sampleId
     *         - the id of the sample entity.
     * @return - with {@link SampleResponse}.
     * @throws BaseException
     *         - when error occurs.
     */
    @GET
    @Operation(summary = SampleRestInformation.GET_SAMPLE_SUMMARY, description = SampleRestInformation.GET_SAMPLE_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(SamplePath.ID)
    SampleResponse getSample(@PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SampleRestInformation.SAMPLE_PARAM_ID_DESCRIPTION,
            required = true) String sampleId) throws BaseException;

    /**
     * Sample http POST method for saving sample entity.
     *
     * @param sampleRequest
     *         - the request that contains information about the sample entity.
     * @return - with {@link SampleResponse}.
     * @throws BaseException
     *         - when error occurs.
     */
    @POST
    @Operation(summary = SampleRestInformation.POST_SAMPLE_SUMMARY, description = SampleRestInformation.POST_SAMPLE_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    SampleResponse postSample(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) SampleRequest sampleRequest) throws BaseException;
}
