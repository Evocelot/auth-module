package hu.evocelot.auth.common.rest.filter;

import jakarta.annotation.Priority;

/**
 * Custom {@link Priority} annotation values.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public interface CustomPriorities {

    /**
     * Priority of the filter/interceptor running before authentication.
     */
    int PRE_AUTHENTICATION = 500;
}
