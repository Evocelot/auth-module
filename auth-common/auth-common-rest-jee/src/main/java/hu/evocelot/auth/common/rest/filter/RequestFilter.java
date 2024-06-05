package hu.evocelot.auth.common.rest.filter;

import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.rest.cdi.RequestContainer;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.lang3.StringUtils;

/**
 * Common utility request filter. Primary target is processing http request headers. Must be run before exception handlers, for correct error codes
 * language translating (see: {@link PreMatching})
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
@PreMatching
@Priority(CustomPriorities.PRE_AUTHENTICATION)
public class RequestFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        RequestContainer requestContainer = CDI.current().select(RequestContainer.class).get();
        ProjectHeader header = ProjectHeader.readHeaders(requestContext.getHeaders());

        // Set remote IP if in Forwarded header not specified.
        if (StringUtils.isBlank(header.getForwarded()) && request != null) {
            header.setForwarded(request.getRemoteAddr());
        }

        requestContainer.setProjectHeader(header);
    }
}
