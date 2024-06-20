package hu.evocelot.auth.service.auth.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.SecurityUser;

/**
 * Interface for handling {@link SecurityUser}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface SecurityUserRepository extends EntityRepository<SecurityUser, String> {

    /**
     * Check when the email address is already in use.
     *
     * @param emailAddress
     *         - the email address.
     * @return - with true or false.
     */
    @Query("SELECT CASE WHEN (COUNT(su) > 0) THEN true ELSE false END" + " FROM SecurityUser su WHERE su.emailAddress = :emailAddress")
    boolean emailAddressAlreadyInUse(@QueryParam("emailAddress") String emailAddress);

    /**
     * Check when the security group is under use.
     *
     * @param securityGroupId
     *         - the id of the security group to find.
     * @return - with true, if there is at least one security user with the specified security group.
     */
    @Query("SELECT CASE WHEN (COUNT(su) > 0) THEN true ELSE false END" + " FROM SecurityUser su WHERE su.securityGroup.id = :securityGroupId")
    boolean securityGroupIsUnderUse(@QueryParam("securityGroupId") String securityGroupId);

    /**
     * Finds the {@link SecurityUser} based on the email address.
     *
     * @param emailAddress
     *         - the email address.
     */
    @Query("SELECT su FROM SecurityUser su WHERE su.emailAddress = :emailAddress")
    SecurityUser findByEmailAddress(@QueryParam("emailAddress") String emailAddress);
}
