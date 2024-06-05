package hu.evocelot.auth.common.rest.cdi;

import java.util.Map;

import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.icellmobilsoft.coffee.rest.cdi.BaseRequestContainer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 * Common request scope container.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class RequestContainer {

    /**
     * Http request parsed http header object.
     */
    private ProjectHeader projectHeader;

    /**
     * Default Http request parsed http header object.
     */
    private final ProjectHeader defaultProjectHeader = new ProjectHeader();

    @Inject
    private BaseRequestContainer baseRequestContainer;

    /**
     * Get http request parsed http header object.
     *
     * @return - with the parsed http header object.
     */
    @Produces
    @RequestScoped
    public ProjectHeader getProjectHeader() {
        // There are cases when the header is not loaded, yet we still access the dictionary
        // In this case, WELD-000052 error occurred
        if (projectHeader == null) {
            return defaultProjectHeader;
        }
        return projectHeader;
    }

    /**
     * Set parsed http header object.
     *
     * @param projectHeader
     *         - the parsed http header object.
     */
    public void setProjectHeader(ProjectHeader projectHeader) {
        this.projectHeader = projectHeader;
    }

    /**
     * Get all container object from all scope storage.
     *
     * @return - with all the key/value from all scope storage.
     * @see BaseRequestContainer#getObjectMap()
     */
    public Map<String, Object> getObjectMap() {
        return baseRequestContainer.getObjectMap();
    }

    /**
     * Getter for the field requestObject.
     *
     * @return - with the Http request object.
     * @see BaseRequestContainer#getRequestObject()
     */
    public Object getRequestObject() {
        return baseRequestContainer.getRequestObject();
    }

    /**
     * Setter for the field requestObject.
     *
     * @param requestObject
     *         - the http request object.
     * @see BaseRequestContainer#setRequestObject(Object)
     */
    public void setRequestObject(Object requestObject) {
        baseRequestContainer.setRequestObject(requestObject);
    }
}
