package hu.evocelot.auth.sample.jpaservice.action;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.evocelot.auth.sample.jpaservice.converter.SampleCoreTypeConverter;
import hu.evocelot.auth.sample.jpaservice.converter.SampleTypeConverter;
import hu.evocelot.auth.sample.jpaservice.service.SampleEntityService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleRequest;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.sample.SampleEntity;
import hu.evocelot.auth.model.sample.enums.SampleStatus;

/**
 * Sample post action for saving Sample entity.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class JpaSamplePostAction extends BaseAction {

    @Inject
    private SampleEntityService sampleEntityService;

    @Inject
    private SampleCoreTypeConverter sampleCoreTypeConverter;

    @Inject
    private SampleTypeConverter sampleTypeConverter;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * Sample method for save sample entity.
     *
     * @param sampleRequest
     *         - the request that contains information about the entity to save.
     * @return - with {@link SampleResponse}.
     * @throws BaseException
     *         - when error occurs.
     */
    public SampleResponse postSample(SampleRequest sampleRequest) throws BaseException {
        if (Objects.isNull(sampleRequest)) {
            throw new InvalidParameterException("The sampleRequest is null!");
        } else if (Objects.isNull(sampleRequest.getSample())) {
            throw new InvalidParameterException("The sampleRequest.getSample is null!");
        }

        SampleEntity entity = sampleCoreTypeConverter.convert(sampleRequest.getSample());
        entity.setStatus(SampleStatus.DONE);
        entity.setLocalDateTime(LocalDateTime.now());
        SampleEntity finalEntity = entity;
        entity = transactionHelper.executeWithTransaction(() -> createOneNeedTransaction(finalEntity));

        SampleResponse response = new SampleResponse();
        response.setSample(sampleTypeConverter.convert(entity));

        handleSuccessResultType(response, sampleRequest);
        return response;
    }

    private SampleEntity createOneNeedTransaction(SampleEntity entity) throws BaseException {
        return sampleEntityService.save(entity);
    }
}
