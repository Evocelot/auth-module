package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.repository.SecurityGroupRepository;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Service class for handling {@link SecurityGroup}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupService extends BaseService<SecurityGroup> {

    @Inject
    private SecurityGroupRepository securityGroupRepository;

    /**
     * Check when the security group name is already in use.
     *
     * @param securityGroupName
     *         - the name of the security group.
     * @return - true if there is at least one security group with the given name.
     * @throws BaseException
     *         - when an error occurs.
     */
    public boolean nameAlreadyInUse(String securityGroupName) throws BaseException {
        return wrapValidated(securityGroupRepository::nameAlreadyInUse, securityGroupName, "nameAlreadyInUse", "securityGroupName");
    }
}
