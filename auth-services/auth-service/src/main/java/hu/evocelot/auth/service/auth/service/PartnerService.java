package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.repository.PartnerRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link Partner}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PartnerService extends BaseService<Partner> {

    @Inject
    private PartnerRepository partnerRepository;

    /**
     * Finds the {@link Partner} entity based on the security user id. Fetch the {@link SecurityUser} entity into the partner.
     *
     * @param securityUserId
     *         - the id of the {@link SecurityUser}.
     * @return - with the {@link Partner}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Partner findBySecurityUserIdFetchSecurityUser(String securityUserId) throws BaseException {
        return wrapValidated(partnerRepository::findBySecurityUserIdFetchSecurityUser,
                securityUserId,
                "findBySecurityUserIdFetchSecurityUser",
                "securityUserId");
    }

    /**
     * Finds the {@link Partner} entity based on the security user id.
     *
     * @param securityUserId
     *         - the id of the {@link SecurityUser}.
     * @return - with the {@link Partner}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Partner findBySecurityUserId(String securityUserId) throws BaseException {
        return wrapValidated(partnerRepository::findBySecurityUserId, securityUserId, "findBySecurityUserId", "securityUserId");
    }
}
