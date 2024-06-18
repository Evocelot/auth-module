package hu.evocelot.auth.service.auth.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.SecurityGroup;

/**
 * Interface for handling {@link SecurityGroup}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface SecurityGroupRepository extends EntityRepository<SecurityGroup, String> {

    /**
     * Check when the name is already in use.
     *
     * @param name
     *         - the name of the security group.
     * @return - with true or false.
     */
    @Query("SELECT CASE WHEN (COUNT(sg) > 0) THEN true ELSE false END" + " FROM SecurityGroup sg WHERE sg.name = :name")
    boolean nameAlreadyInUse(@QueryParam("name") String name);
}
