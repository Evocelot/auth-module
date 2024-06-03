package hu.evocelot.auth.common.rest.cdi;

import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

/**
 * Application scoped container class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@ApplicationScoped
public class ApplicationContainer {

    /**
     * Application scope storage key/values.
     */
    private Map<String, Object> objectMap;

    /**
     * Get application scope storage key/values.
     *
     * @return Application scope storage key/values.
     */
    public Map<String, Object> getObjectMap() {
        if (objectMap == null) {
            objectMap = new HashMap<>();
        }
        return objectMap;
    }
}
