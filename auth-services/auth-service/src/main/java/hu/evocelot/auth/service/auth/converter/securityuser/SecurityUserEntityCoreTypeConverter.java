package hu.evocelot.auth.service.auth.converter.securityuser;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserEntityCoreType;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserStatusEnumType;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.model.enums.SecurityUserStatus;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;
import hu.icellmobilsoft.coffee.tool.utils.enums.EnumUtil;

/**
 * Converter class that handles conversion between {@link SecurityUser} and {@link SecurityUserEntityCoreType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityUserEntityCoreTypeConverter implements IEntityConverter<SecurityUser, SecurityUserEntityCoreType> {

    @Override
    public SecurityUserEntityCoreType convert(SecurityUser entity) throws BaseException {
        SecurityUserEntityCoreType dto = new SecurityUserEntityCoreType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public SecurityUser convert(SecurityUserEntityCoreType securityUserEntityCoreType) throws BaseException {
        SecurityUser entity = new SecurityUser();
        convert(entity, securityUserEntityCoreType);
        return entity;
    }

    @Override
    public void convert(SecurityUserEntityCoreType destinationDto, SecurityUser sourceEntity) throws BaseException {
        destinationDto.setSecurityGroupId(sourceEntity.getSecurityGroup().getId());
        destinationDto.setStatus(EnumUtil.convert(sourceEntity.getStatus(), SecurityUserStatusEnumType.class));
    }

    @Override
    public void convert(SecurityUser destinationEntity, SecurityUserEntityCoreType sourceDto) throws BaseException {
        destinationEntity.setStatus(EnumUtil.convert(sourceDto.getStatus(), SecurityUserStatus.class));
    }
}
