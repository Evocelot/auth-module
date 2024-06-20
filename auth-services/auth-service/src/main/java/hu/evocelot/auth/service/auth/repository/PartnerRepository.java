package hu.evocelot.auth.service.auth.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;

import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityUser;

/**
 * Interface for handling {@link Partner}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Repository
public interface PartnerRepository extends EntityRepository<Partner, String> {

    /**
     * Finds a {@link Partner} by the given security user ID, fetching the associated {@link SecurityUser} eagerly.
     *
     * @param securityUserId
     *         the ID of the security user to find the associated partner.
     * @return the {@link Partner} entity with the given security user ID.
     */
    @Query("SELECT p FROM Partner p JOIN FETCH p.securityUser WHERE p.securityUser.id = :securityUserId")
    Partner findBySecurityUserIdFetchSecurityUser(@QueryParam("securityUserId") String securityUserId);

    /**
     * Finds a {@link Partner} by the given security user ID.
     *
     * @param securityUserId
     *         the ID of the security user to find the associated partner.
     * @return the {@link Partner} entity with the given security user ID.
     */
    @Query("SELECT p FROM Partner p WHERE p.securityUser.id = :securityUserId")
    Partner findBySecurityUserId(@QueryParam("securityUserId") String securityUserId);
}
