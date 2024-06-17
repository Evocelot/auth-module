package hu.evocelot.auth.service.auth.converter.permission;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionEntityCoreType;
import hu.evocelot.auth.model.Permission;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link Permission} and {@link PermissionEntityCoreType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionEntityCoreTypeConverter implements IEntityConverter<Permission, PermissionEntityCoreType> {

    @Override
    public PermissionEntityCoreType convert(Permission entity) throws BaseException {
        PermissionEntityCoreType dto = new PermissionEntityCoreType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public Permission convert(PermissionEntityCoreType permissionEntityCoreType) throws BaseException {
        Permission entity = new Permission();
        convert(entity, permissionEntityCoreType);
        return entity;
    }

    @Override
    public void convert(PermissionEntityCoreType destinationDto, Permission sourceEntity) throws BaseException {
        destinationDto.setName(sourceEntity.getName());
        destinationDto.setDescription(sourceEntity.getDescription());
    }

    @Override
    public void convert(Permission destinationEntity, PermissionEntityCoreType sourceDto) throws BaseException {
        destinationEntity.setName(sourceDto.getName());
        destinationEntity.setDescription(sourceDto.getDescription());
    }
}
