package hu.evocelot.auth.ts.sample.rest;

import java.net.URI;

import jakarta.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import hu.evocelot.auth.ts.sample.rest.builder.SampleRequestBuilder;
import hu.icellmobilsoft.coffee.dto.common.commonservice.FunctionCodeType;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.roaster.api.TestSuiteGroup;
import hu.icellmobilsoft.roaster.restassured.BaseConfigurableWeldIT;
import hu.evocelot.auth.api.rest.jee10.ISampleRest;
import hu.evocelot.auth.api.rest.jee10.client.ISampleRestJsonClient;
import hu.evocelot.auth.api.rest.jee10.client.ISampleRestXmlClient;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleResponse;

/**
 * Sample service {@link ISampleRest#postSample(SampleRequest)} test
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@DisplayName("Testing Sample service POST with JAX-RS")
@Tag(TestSuiteGroup.JAXRS)
class PostSampleJaxrsIT extends BaseConfigurableWeldIT {

    private static final String REST_CONFIG_KEY = "auth.service.sample.base.uri";

    @Inject
    private SampleRequestBuilder requestBuilder;

    @Inject
    @ConfigProperty(name = REST_CONFIG_KEY)
    private String baseUri;

    @Test
    @DisplayName("POST default request JSON")
    void testDefaultPostJson() throws BaseException {
        ISampleRestJsonClient apiImpl = RestClientBuilder.newBuilder()
                // set URI
                .baseUri(URI.create(baseUri))
                // build API interface
                .build(ISampleRestJsonClient.class);
        SampleResponse response = apiImpl.postSample(requestBuilder.getDefault());
        Assertions.assertEquals(FunctionCodeType.OK, response.getFuncCode());
    }

    @Test
    @DisplayName("POST default request XML")
    void testDefaultPostXml() throws BaseException {
        ISampleRestXmlClient apiImpl = RestClientBuilder.newBuilder()
                // set URI
                .baseUri(URI.create(baseUri))
                // build API interface
                .build(ISampleRestXmlClient.class);
        SampleResponse response = apiImpl.postSample(requestBuilder.getDefault());
        Assertions.assertEquals(FunctionCodeType.OK, response.getFuncCode());
    }
}
