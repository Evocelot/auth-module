package hu.evocelot.auth.sample.jpaservice.converter;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import hu.icellmobilsoft.coffee.system.jpa.converter.IResponseConverter;
import hu.icellmobilsoft.coffee.tool.utils.enums.EnumUtil;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleStatusEnumType;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleType;
import hu.evocelot.auth.model.sample.SampleEntity;
import hu.evocelot.auth.model.sample.enums.SampleStatus;

/**
 * handles conversion between {@link SampleEntity} and {@link SampleType}.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class SampleTypeConverter implements IResponseConverter<SampleEntity, SampleType> {

    @Inject
    private SampleCoreTypeConverter coreTypeConverter;

    @Override
    public SampleType convert(SampleEntity entity) {
        SampleType result = new SampleType();
        convert(result, entity);
        return result;
    }

    @Override
    public SampleEntity convert(SampleType sampleType) {
        SampleEntity result = new SampleEntity();
        convert(sampleType, result);
        return result;
    }

    @Override
    public void convert(SampleType destinationDto, SampleEntity sourceEntity) {
        coreTypeConverter.convert(destinationDto, sourceEntity);
        destinationDto.setSampleId(sourceEntity.getId());
        destinationDto.setSampleStatus(EnumUtil.convert(sourceEntity.getStatus(), SampleStatusEnumType.class));
    }

    @Override
    public void convert(SampleEntity destinationEntity, SampleType sourceDto) {
        coreTypeConverter.convert(destinationEntity, sourceDto);
        destinationEntity.setStatus(EnumUtil.convert(sourceDto.getSampleStatus(), SampleStatus.class));
    }
}
