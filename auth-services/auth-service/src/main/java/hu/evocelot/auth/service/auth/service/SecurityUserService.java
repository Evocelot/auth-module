package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.repository.SecurityUserRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link SecurityUser}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityUserService extends BaseService<SecurityUser> {

    @Inject
    private SecurityUserRepository securityUserRepository;

    /**
     * Check when the email address is already in use.
     *
     * @param emailAddress
     *         - the email address.
     * @return - with true or false.
     */
    public boolean emailAddressAlreadyInUse(String emailAddress) throws BaseException {
        return wrapValidated(securityUserRepository::emailAddressAlreadyInUse, emailAddress, "emailAddressAlreadyInUse", "emailAddress");
    }
}
