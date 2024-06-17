package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.Permission;
import hu.evocelot.auth.service.auth.repository.PermissionRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link Permission}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionService extends BaseService<Permission> {

    @Inject
    private PermissionRepository permissionRepository;

    /**
     * Check when the permission name is already in use.
     *
     * @param permissionName
     *         - the name of the permission.
     * @return - true if there is at least one permission with the given name.
     */
    public boolean nameAlreadyInUse(String permissionName) throws BaseException {
        return wrapValidated(permissionRepository::nameAlreadyInUse, permissionName, "nameAlreadyInUse", "permissionName");
    }
}
