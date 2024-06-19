package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryRequest;
import hu.evocelot.auth.api.permissionquery._1_0.rest.permission_query.PermissionQueryResponse;
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

    /**
     * HTTP GET method for getting the permission.
     *
     * @param permissionId
     *         - the id of the permission.
     * @return - with {@link PermissionResponse} that contains the permission details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @GET
    @Operation(summary = PermissionRestInformation.GET_PERMISSION_SUMMARY, description = PermissionRestInformation.GET_PERMISSION_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    PermissionResponse getPermission(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = PermissionRestInformation.PERMISSION_ID_PARAM_SUMMARY,
                    required = true) String permissionId) throws BaseException;

    /**
     * HTTP PUT method for modifying the permission.
     *
     * @param permissionId
     *         - the if of the permission to modify.
     * @param permissionRequest
     *         - the request that contains the updated details of the permission.
     * @return - with {@link PermissionResponse} that contains the updated details of the permission.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = PermissionRestInformation.UPDATE_PERMISSION_SUMMARY, description = PermissionRestInformation.UPDATE_PERMISSION_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    PermissionResponse updatePermission(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = PermissionRestInformation.PERMISSION_ID_PARAM_SUMMARY,
                    required = true) String permissionId, @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) PermissionRequest permissionRequest)
            throws BaseException;

    /**
     * HTTP POST method for listing permissions.
     *
     * @param permissionQueryRequest
     *         - the request that contains information about the filtering, sorting and paging details.
     * @return - with {@link PermissionResponse} that contains the relevant permissions.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = PermissionRestInformation.QUERY_PERMISSION_SUMMARY, description = PermissionRestInformation.QUERY_PERMISSION_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.QUERY)
    PermissionQueryResponse queryPermission(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) PermissionQueryRequest permissionQueryRequest)
            throws BaseException;
}
