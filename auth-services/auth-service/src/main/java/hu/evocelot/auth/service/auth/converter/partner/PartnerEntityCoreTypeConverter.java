package hu.evocelot.auth.service.auth.converter.partner;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerEntityCoreType;
import hu.evocelot.auth.model.Partner;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link Partner} and {@link PartnerEntityCoreType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PartnerEntityCoreTypeConverter implements IEntityConverter<Partner, PartnerEntityCoreType> {

    @Override
    public PartnerEntityCoreType convert(Partner entity) throws BaseException {
        PartnerEntityCoreType dto = new PartnerEntityCoreType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public Partner convert(PartnerEntityCoreType partnerEntityCoreType) throws BaseException {
        Partner entity = new Partner();
        convert(entity, partnerEntityCoreType);
        return entity;
    }

    @Override
    public void convert(PartnerEntityCoreType destinationDto, Partner sourceEntity) throws BaseException {
        destinationDto.setFirstName(sourceEntity.getFirstName());
        destinationDto.setLastName(sourceEntity.getLastName());
        destinationDto.setPhoneNumber(sourceEntity.getPhoneNumber());
    }

    @Override
    public void convert(Partner destinationEntity, PartnerEntityCoreType sourceDto) throws BaseException {
        destinationEntity.setFirstName(sourceDto.getFirstName());
        destinationEntity.setLastName(sourceDto.getLastName());
        destinationEntity.setPhoneNumber(sourceDto.getPhoneNumber());
    }
}
