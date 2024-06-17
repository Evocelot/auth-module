package hu.evocelot.auth.service.auth.service;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.common.system.jpa.service.BaseService;
import hu.evocelot.auth.model.SecurityGroup;

/**
 * Service class for handling {@link SecurityGroup}s.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupService extends BaseService<SecurityGroup> {

}
