package hu.evocelot.auth.common.rest.exception;

import hu.icellmobilsoft.coffee.rest.exception.DefaultExceptionMessageTranslator;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import jakarta.interceptor.Interceptor;

/**
 * Exception translator implementation for exception throwing.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.APPLICATION + 10)
public class ExceptionMessageTranslator extends DefaultExceptionMessageTranslator {

}
