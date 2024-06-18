package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.evocelot.auth.service.auth.repository.PermissionToSecurityGroupRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link PermissionToSecurityGroup}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PermissionToSecurityGroupService extends BaseService<PermissionToSecurityGroup> {

    @Inject
    private PermissionToSecurityGroupRepository permissionToSecurityGroupRepository;

    /**
     * For checking the security group has the defined permission.
     *
     * @param securityGroupId
     *         - the id of the security group.
     * @param permissionId
     *         - the id of the permission.
     * @return - with true, if the security group has the permission.
     * @throws BaseException
     *         - when an error occurs.
     */
    public boolean hasPermission(String securityGroupId, String permissionId) throws BaseException {
        return wrapValidated(permissionToSecurityGroupRepository::hasPermission,
                securityGroupId,
                permissionId,
                "hasPermission",
                "securityGroupId",
                "permissionId");
    }

    /**
     * For deleting PermissionToSecurityGroup based on the ids.
     *
     * @param securityGroupId
     *         - the id of the security group.
     * @param permissionId
     *         - the id of the permission.
     * @return - with the number of the deleted rows.
     * @throws BaseException
     *         - when an error occurs.
     */
    public int deleteByIds(String securityGroupId, String permissionId) throws BaseException {
        return wrapValidated(permissionToSecurityGroupRepository::deleteByIds,
                securityGroupId,
                permissionId,
                "deleteByIds",
                "securityGroupId",
                "permissionId");
    }
}
