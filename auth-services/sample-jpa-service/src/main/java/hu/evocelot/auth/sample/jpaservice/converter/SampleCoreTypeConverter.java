package hu.evocelot.auth.sample.jpaservice.converter;

import jakarta.enterprise.inject.Model;

import hu.icellmobilsoft.coffee.system.jpa.converter.IResponseConverter;
import hu.icellmobilsoft.coffee.tool.utils.enums.EnumUtil;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleCoreType;
import hu.evocelot.auth.api.sample._1_0.rest.post.SampleValueEnumType;
import hu.evocelot.auth.model.sample.SampleEntity;

/**
 * Handles conversion between {@link SampleEntity} and {@link SampleCoreType}.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class SampleCoreTypeConverter implements IResponseConverter<SampleEntity, SampleCoreType> {

    @Override
    public SampleCoreType convert(SampleEntity entity) {
        SampleCoreType result = new SampleCoreType();
        convert(result, entity);
        return result;
    }

    @Override
    public SampleEntity convert(SampleCoreType sampleCoreType) {
        SampleEntity result = new SampleEntity();
        convert(result, sampleCoreType);
        return result;
    }

    @Override
    public void convert(SampleCoreType destinationDto, SampleEntity sourceEntity) {
        destinationDto.setColumnA(sourceEntity.getInputValue());
        destinationDto.setColumnB(EnumUtil.convert(sourceEntity.getValue(), SampleValueEnumType.class));
    }

    @Override
    public void convert(SampleEntity destinationEntity, SampleCoreType sourceDto) {
        destinationEntity.setInputValue(sourceDto.getColumnA());
    }
}
