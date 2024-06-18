package hu.evocelot.auth.service.auth.converter.securitygroup;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupEntityCoreType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link SecurityGroup} and {@link SecurityGroupEntityCoreType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupEntityCoreTypeConverter implements IEntityConverter<SecurityGroup, SecurityGroupEntityCoreType> {

    @Override
    public SecurityGroupEntityCoreType convert(SecurityGroup entity) throws BaseException {
        SecurityGroupEntityCoreType dto = new SecurityGroupEntityCoreType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public SecurityGroup convert(SecurityGroupEntityCoreType securityGroupEntityCoreType) throws BaseException {
        SecurityGroup entity = new SecurityGroup();
        convert(entity, securityGroupEntityCoreType);
        return entity;
    }

    @Override
    public void convert(SecurityGroupEntityCoreType destinationDto, SecurityGroup sourceEntity) throws BaseException {
        destinationDto.setName(sourceEntity.getName());
        destinationDto.setDescription(sourceEntity.getDescription());
    }

    @Override
    public void convert(SecurityGroup destinationEntity, SecurityGroupEntityCoreType sourceDto) throws BaseException {
        destinationEntity.setName(sourceDto.getName());
        destinationEntity.setDescription(sourceDto.getDescription());
    }
}
