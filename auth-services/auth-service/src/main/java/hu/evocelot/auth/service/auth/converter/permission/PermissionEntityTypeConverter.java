package hu.evocelot.auth.service.auth.converter.permission;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionEntityType;
import hu.evocelot.auth.model.Permission;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.system.jpa.converter.IEntityConverter;

/**
 * Converter class that handles conversion between {@link Permission} and {@link PermissionEntityType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionEntityTypeConverter implements IEntityConverter<Permission, PermissionEntityType> {

    @Inject
    private PermissionEntityCoreTypeConverter permissionEntityCoreTypeConverter;

    @Override
    public PermissionEntityType convert(Permission entity) throws BaseException {
        PermissionEntityType dto = new PermissionEntityType();
        convert(dto, entity);
        return dto;
    }

    @Override
    public Permission convert(PermissionEntityType permissionEntityType) throws BaseException {
        Permission entity = new Permission();
        convert(entity, permissionEntityType);
        return entity;
    }

    @Override
    public void convert(PermissionEntityType destinationDto, Permission sourceEntity) throws BaseException {
        permissionEntityCoreTypeConverter.convert(destinationDto, sourceEntity);

        destinationDto.setPermissionId(sourceEntity.getId());
    }

    @Override
    public void convert(Permission destinationEntity, PermissionEntityType sourceDto) throws BaseException {
        permissionEntityCoreTypeConverter.convert(destinationEntity, sourceDto);
    }
}
