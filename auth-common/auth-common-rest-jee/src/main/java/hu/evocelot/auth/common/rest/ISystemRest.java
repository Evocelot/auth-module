package hu.evocelot.auth.common.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;

import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.api.common.path.IServicePath;

/**
 * REST endpoint for system service functions.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Path("")
public interface ISystemRest {

    /**
     * META-INF/MANIFEST.MF content in Rest response.
     *
     * @param servletRequest
     *         - the system servlet request.
     * @return - with META-INF/MANIFEST.MF content in text format.
     * @throws BaseException
     *         - when error occurs.
     */
    @Operation(hidden = true)
    @GET
    @Path(IServicePath.VERSION_INFO)
    @Produces(MediaType.TEXT_PLAIN)
    String versionInfo(@Context HttpServletRequest servletRequest) throws BaseException;
}
