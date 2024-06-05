package hu.evocelot.auth.common.system.rest.rest;

import hu.icellmobilsoft.coffee.cdi.logger.AppLogger;
import hu.icellmobilsoft.coffee.cdi.logger.ThisLogger;
import jakarta.inject.Inject;

/**
 * Base REST service for all REST endpoint.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public abstract class BaseRestService extends hu.icellmobilsoft.coffee.rest.rest.BaseRestService {

    @Inject
    @ThisLogger
    private AppLogger log;
}
