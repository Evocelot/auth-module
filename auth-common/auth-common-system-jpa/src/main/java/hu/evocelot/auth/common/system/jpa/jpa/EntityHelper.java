package hu.evocelot.auth.common.system.jpa.jpa;

import hu.icellmobilsoft.coffee.model.base.annotation.CurrentUser;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;

/**
 * Entity helper class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Named
@Dependent
public class EntityHelper extends hu.icellmobilsoft.coffee.jpa.sql.entity.EntityHelper {

    /**
     * Default system user identifier.
     */
    public static final String DEFAULT_SYSTEM_USER = "0";

    /**
     * Audit user producer.
     *
     * @return - with the customerUser id.
     */
    @Produces
    @CurrentUser
    public String currentUser() {
        return DEFAULT_SYSTEM_USER;
    }
}
