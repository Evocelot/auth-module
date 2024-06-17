package hu.evocelot.auth.service.auth.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.Permission;

/**
 * Interface for handling {@link Permission}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface PermissionRepository extends EntityRepository<Permission, String> {

    /**
     * Check when the name is already in use.
     *
     * @param name
     *         - the name of the permission.
     * @return - with true or false.
     */
    @Query("SELECT CASE WHEN (COUNT(p) > 0) THEN true ELSE false END" + " FROM Permission p WHERE p.name = :name")
    boolean nameAlreadyInUse(@QueryParam("name") String name);
}
