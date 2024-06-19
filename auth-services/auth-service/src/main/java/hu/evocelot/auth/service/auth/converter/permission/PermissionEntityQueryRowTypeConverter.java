package hu.evocelot.auth.service.auth.converter.permission;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionEntityQueryRowType;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link PermissionToSecurityGroup} and {@link PermissionEntityQueryRowType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionEntityQueryRowTypeConverter implements IEntityConverter<PermissionToSecurityGroup, PermissionEntityQueryRowType> {

    @Override
    public PermissionEntityQueryRowType convert(PermissionToSecurityGroup entity) throws BaseException {
        PermissionEntityQueryRowType dto = new PermissionEntityQueryRowType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public PermissionToSecurityGroup convert(PermissionEntityQueryRowType permissionEntityQueryRowType) throws BaseException {
        PermissionToSecurityGroup entity = new PermissionToSecurityGroup();
        convert(entity, permissionEntityQueryRowType);
        return entity;
    }

    @Override
    public void convert(PermissionEntityQueryRowType destinationDto, PermissionToSecurityGroup sourceEntity) throws BaseException {
        destinationDto.setPermissionId(sourceEntity.getPermission().getId());
        destinationDto.setSecurityGroupId(sourceEntity.getSecurityGroup().getId());
    }

    @Override
    public void convert(PermissionToSecurityGroup destinationEntity, PermissionEntityQueryRowType sourceDto) throws BaseException {

    }
}
