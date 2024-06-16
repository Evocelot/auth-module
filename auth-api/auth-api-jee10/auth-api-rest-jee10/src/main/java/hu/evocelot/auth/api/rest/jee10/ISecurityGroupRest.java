package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Path;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.SecurityGroupRestInformation;

/**
 * REST endpoint for security group management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = SecurityGroupRestInformation.TAG, description = SecurityGroupRestInformation.DESCRIPTION)
@Path(AuthPath.SECURITY_GROUP_SERVICE)
public interface ISecurityGroupRest {

}
