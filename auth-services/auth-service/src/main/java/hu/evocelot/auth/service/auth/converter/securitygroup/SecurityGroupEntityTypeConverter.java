package hu.evocelot.auth.service.auth.converter.securitygroup;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupEntityType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link SecurityGroup} and {@link SecurityGroupEntityType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupEntityTypeConverter implements IEntityConverter<SecurityGroup, SecurityGroupEntityType> {

    @Inject
    private SecurityGroupEntityCoreTypeConverter securityGroupEntityCoreTypeConverter;

    @Override
    public SecurityGroupEntityType convert(SecurityGroup entity) throws BaseException {
        SecurityGroupEntityType dto = new SecurityGroupEntityType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public SecurityGroup convert(SecurityGroupEntityType securityGroupEntityType) throws BaseException {
        SecurityGroup entity = new SecurityGroup();
        convert(entity, securityGroupEntityType);
        return entity;
    }

    @Override
    public void convert(SecurityGroupEntityType destinationDto, SecurityGroup sourceEntity) throws BaseException {
        securityGroupEntityCoreTypeConverter.convert(destinationDto, sourceEntity);

        destinationDto.setSecurityGroupId(sourceEntity.getId());
    }

    @Override
    public void convert(SecurityGroup destinationEntity, SecurityGroupEntityType sourceDto) throws BaseException {
        securityGroupEntityCoreTypeConverter.convert(destinationEntity, sourceDto);
    }
}
