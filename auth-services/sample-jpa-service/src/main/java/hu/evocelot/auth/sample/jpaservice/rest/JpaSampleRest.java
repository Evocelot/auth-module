package hu.evocelot.auth.sample.jpaservice.rest;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.evocelot.auth.sample.jpaservice.action.JpaSampleGetAction;
import hu.evocelot.auth.sample.jpaservice.action.JpaSamplePostAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.api.rest.jee10.ISampleRest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleResponse;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;

/**
 * sample service jpa implementation.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class JpaSampleRest extends BaseRestService implements ISampleRest {

    @Inject
    private JpaSampleGetAction jpaSampleGetAction;

    @Inject
    private JpaSamplePostAction jpaSamplePostAction;

    @Override
    public SampleResponse getSample(String sampleId) throws BaseException {
        return wrapPathParam1(jpaSampleGetAction::sample, sampleId, "getSample", "sampleId");
    }

    @Override
    public SampleResponse postSample(SampleRequest sampleRequest) throws BaseException {
        return wrapPathParam1(jpaSamplePostAction::postSample, sampleRequest, "postSample", "sampleRequest");
    }
}
