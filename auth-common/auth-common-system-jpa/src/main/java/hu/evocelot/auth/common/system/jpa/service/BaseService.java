package hu.evocelot.auth.common.system.jpa.service;

import jakarta.enterprise.context.Dependent;

/**
 * Base service parent for JPA exception handled functions.
 *
 * @param <T>
 *         - the entity class.
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Dependent
public class BaseService<T> extends hu.icellmobilsoft.coffee.jpa.service.BaseService<T> {

}
