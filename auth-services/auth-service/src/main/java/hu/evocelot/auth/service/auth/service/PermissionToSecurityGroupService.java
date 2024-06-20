package hu.evocelot.auth.service.auth.service;

import java.util.List;

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

    /**
     * For getting the permission list for the security group based on the id. Fetches the permission.
     *
     * @param securityGroupId
     *         - the id of the owner security group.
     * @return - with the list of the {@link PermissionToSecurityGroup}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public List<PermissionToSecurityGroup> findBySecurityGroupIdFetchPermission(String securityGroupId) throws BaseException {
        return wrapValidated(permissionToSecurityGroupRepository::findBySecurityGroupIdFetchPermission,
                securityGroupId,
                "findBySecurityGroupIdFetchPermission",
                "securityGroupId");
    }

    /**
     * Finds the security group ids by permission id.
     *
     * @param permissionId
     *         - the id of the permission.
     * @return - with the list of SecurityGroups id.
     * @throws BaseException
     *         - when an error occurs.
     */
    public List<String> getSecurityGroupIdsByPermissionId(String permissionId) throws BaseException {
        return wrapValidated(permissionToSecurityGroupRepository::getSecurityGroupIdsByPermissionId,
                permissionId,
                "getSecurityGroupIdsByPermissionId",
                "permissionId");
    }
}
