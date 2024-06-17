package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.PermissionRestInformation;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionRequest;
import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionResponse;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for permission management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = PermissionRestInformation.TAG, description = PermissionRestInformation.DESCRIPTION)
@Path(AuthPath.PERMISSION_SERVICE)
public interface IPermissionRest {

    @GET
    @Operation(summary = PermissionRestInformation.GET_PERMISSION_SUMMARY, description = PermissionRestInformation.GET_PERMISSION_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    PermissionResponse getPermission(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = PermissionRestInformation.PERMISSION_ID_PARAM_SUMMARY,
                    required = true) String permissionId) throws BaseException;

    @PUT
    @Operation(summary = PermissionRestInformation.UPDATE_PERMISSION_SUMMARY, description = PermissionRestInformation.UPDATE_PERMISSION_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    PermissionResponse updatePermission(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = PermissionRestInformation.PERMISSION_ID_PARAM_SUMMARY,
                    required = true) String permissionId, @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) PermissionRequest permissionRequest)
            throws BaseException;
}
