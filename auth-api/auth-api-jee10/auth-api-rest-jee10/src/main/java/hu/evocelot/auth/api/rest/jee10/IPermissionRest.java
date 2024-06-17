package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.PermissionRestInformation;

/**
 * REST endpoint for permission management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = PermissionRestInformation.TAG, description = PermissionRestInformation.DESCRIPTION)
@Path(AuthPath.PERMISSION_SERVICE)
public interface IPermissionRest {

}
