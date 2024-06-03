package hu.evocelot.auth.common.rest.cdi;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

/**
 * Application container.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Named
@Dependent
public class AppContainer {

    /**
     * Request scope container.
     */
    @Inject
    private RequestContainer requestContainer;

    /**
     * Get request scope container.
     *
     * @return Request scope container.
     */
    public RequestContainer getRequestContainer() {
        return requestContainer;
    }

    /**
     * Set request scope container.
     *
     * @param requestContainer
     *         request scope container.
     */
    public void setRequestContainer(RequestContainer requestContainer) {
        this.requestContainer = requestContainer;
    }
}
