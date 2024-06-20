package hu.evocelot.auth.service.auth.repository;

import java.util.List;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Modifying;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.PermissionToSecurityGroup;

/**
 * Interface for handling {@link PermissionToSecurityGroup}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface PermissionToSecurityGroupRepository extends EntityRepository<PermissionToSecurityGroup, String> {

    /**
     * For checking the security group has the defined permission.
     *
     * @param securityGroupId
     *         - the id of the security group.
     * @param permissionId
     *         - the id of the permission.
     * @return - with true, if the security group has the permission.
     */
    @Query("SELECT CASE WHEN (COUNT(ptsg) > 0) THEN true ELSE false END"
            + " FROM PermissionToSecurityGroup ptsg WHERE ptsg.securityGroup.id = :securityGroupId AND ptsg.permission.id = :permissionId")
    boolean hasPermission(@QueryParam("securityGroupId") String securityGroupId, @QueryParam("permissionId") String permissionId);

    /**
     * For deleting PermissionToSecurityGroup based on the ids.
     *
     * @param securityGroupId
     *         - the id of the security group.
     * @param permissionId
     *         - the id of the permission.
     * @return - with the number of the deleted rows.
     */
    @Query("DELETE FROM PermissionToSecurityGroup ptsg WHERE ptsg.securityGroup.id = :securityGroupId AND ptsg.permission.id = :permissionId")
    @Modifying
    int deleteByIds(@QueryParam("securityGroupId") String securityGroupId, @QueryParam("permissionId") String permissionId);

    /**
     * For getting the permission list for the security group based on the id. Fetches the permission.
     *
     * @param securityGroupId
     *         - the id of the owner security group.
     * @return - with the list of the {@link PermissionToSecurityGroup}.
     */
    @Query("SELECT ptsg FROM PermissionToSecurityGroup ptsg JOIN FETCH ptsg.permission WHERE ptsg.securityGroup.id = :securityGroupId")
    List<PermissionToSecurityGroup> findBySecurityGroupIdFetchPermission(@QueryParam("securityGroupId") String securityGroupId);

    /**
     * Finds the security group ids by permission id.
     *
     * @param permissionId
     *         - the id of the permission.
     * @return - with the list of SecurityGroups id.
     */
    @Query("SELECT ptsg.securityGroup.id FROM PermissionToSecurityGroup ptsg WHERE ptsg.permission.id = :permissionId")
    List<String> getSecurityGroupIdsByPermissionId(@QueryParam("permissionId") String permissionId);

}
