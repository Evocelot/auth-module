package hu.evocelot.auth.common.rest.exception;

import hu.icellmobilsoft.coffee.rest.exception.DefaultGeneralExceptionMapper;
import jakarta.ws.rs.ext.Provider;

/**
 * Exception mapper for non-handled exception throwing.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
public class GeneralExceptionMapper extends DefaultGeneralExceptionMapper {

}
