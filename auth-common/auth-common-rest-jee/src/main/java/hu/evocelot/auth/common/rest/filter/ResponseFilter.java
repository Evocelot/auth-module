package hu.evocelot.auth.common.rest.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

/**
 * Common utility response filter.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {

    @Context
    HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        if (responseContext.getStatus() == Response.Status.UNAUTHORIZED.getStatusCode()) {
            responseContext.getHeaders().putSingle(HttpHeaders.WWW_AUTHENTICATE, "None");
        }
    }
}
