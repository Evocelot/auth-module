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
    @Query("select case when (count(su) > 0) then true else false end" + " from SecurityUser su where su.emailAddress = :emailAddress")
    boolean emailAddressAlreadyInUse(@QueryParam("emailAddress") String emailAddress);
}
