package hu.evocelot.auth.service.auth.converter.securityuser;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserEntityType;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityCoreTypeConverter;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link SecurityUser} and {@link SecurityUserEntityType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityUserEntityTypeConverter implements IEntityConverter<SecurityUser, SecurityUserEntityType> {

    @Inject
    private SecurityUserEntityCoreTypeConverter securityUserEntityCoreTypeConverter;

    @Override
    public SecurityUserEntityType convert(SecurityUser entity) throws BaseException {
        SecurityUserEntityType dto = new SecurityUserEntityType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public SecurityUser convert(SecurityUserEntityType securityUserEntityType) throws BaseException {
        SecurityUser entity = new SecurityUser();
        convert(entity, securityUserEntityType);
        return entity;
    }

    @Override
    public void convert(SecurityUserEntityType destinationDto, SecurityUser sourceEntity) throws BaseException {
        securityUserEntityCoreTypeConverter.convert(destinationDto, sourceEntity);

        destinationDto.setEmailAddress(sourceEntity.getEmailAddress());
        destinationDto.setSecurityUserId(sourceEntity.getId());
    }

    @Override
    public void convert(SecurityUser destinationEntity, SecurityUserEntityType sourceDto) throws BaseException {
        securityUserEntityCoreTypeConverter.convert(destinationEntity, sourceDto);

        destinationEntity.setEmailAddress(sourceDto.getEmailAddress());
    }
}
