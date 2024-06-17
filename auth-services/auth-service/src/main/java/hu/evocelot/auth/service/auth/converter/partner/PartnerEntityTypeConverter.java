package hu.evocelot.auth.service.auth.converter.partner;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerEntityType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityCoreTypeConverter;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link Partner} and {@link PartnerEntityType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PartnerEntityTypeConverter implements IEntityConverter<Partner, PartnerEntityType> {

    @Inject
    private PartnerEntityCoreTypeConverter partnerEntityCoreTypeConverter;

    @Override
    public PartnerEntityType convert(Partner entity) throws BaseException {
        PartnerEntityType dto = new PartnerEntityType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public Partner convert(PartnerEntityType partnerEntityType) throws BaseException {
        Partner entity = new Partner();
        convert(entity, partnerEntityType);
        return entity;
    }

    @Override
    public void convert(PartnerEntityType destinationDto, Partner sourceEntity) throws BaseException {
        partnerEntityCoreTypeConverter.convert(destinationDto, sourceEntity);

        destinationDto.setPartnerId(sourceEntity.getId());
        destinationDto.setProfilePictureId(sourceEntity.getProfilePictureId());
    }

    @Override
    public void convert(Partner destinationEntity, PartnerEntityType sourceDto) throws BaseException {
        partnerEntityCoreTypeConverter.convert(destinationEntity, sourceDto);

        destinationEntity.setProfilePictureId(sourceDto.getProfilePictureId());
    }
}
