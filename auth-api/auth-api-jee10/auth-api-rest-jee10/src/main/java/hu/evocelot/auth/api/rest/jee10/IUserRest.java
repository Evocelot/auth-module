package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.SecurityUserRestInformation;
import hu.evocelot.auth.api.common.restinformation.UserRestInformation;
import hu.evocelot.auth.api.user._1_0.rest.user.CreateUserRequest;
import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for user management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = UserRestInformation.TAG, description = UserRestInformation.DESCRIPTION)
@Path(AuthPath.USER_SERVICE)
public interface IUserRest {

    /**
     * HTTP POST method for creating users. (Security user + partner)
     *
     * @param createUserRequest
     *         - the request that contains information about the entities to create.
     * @return - with {@link UserResponse} that contains information about the created user.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = UserRestInformation.CREATE_USER_SUMMARY, description = UserRestInformation.CREATE_USER_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    UserResponse createUser(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) CreateUserRequest createUserRequest) throws BaseException;

    /**
     * HTTP GET method for getting the user (security user + partner)
     *
     * @param securityUserId
     *         - the id of the security user.
     * @return - with {@link UserResponse} that contains information about the user.
     * @throws BaseException
     *         - when an error occurs.
     */
    @GET
    @Operation(summary = UserRestInformation.GET_USER_SUMMARY, description = UserRestInformation.GET_USER_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.ID)
    UserResponse getUser(@Parameter(description = SecurityUserRestInformation.SECURITY_USER_ID_PARAM_SUMMARY, required = true) @PathParam(
            AuthPath.PARAM_ID) String securityUserId) throws BaseException;

    /**
     * HTTP DELETE method for deleting the user (security user + partner)
     *
     * @param securityUserId
     *         - the id of the security user.
     * @return - with {@link UserResponse} that contains information about the user.
     * @throws BaseException
     *         - when an error occurs.
     */
    @DELETE
    @Operation(summary = UserRestInformation.DELETE_USER_SUMMARY, description = UserRestInformation.DELETE_USER_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.ID)
    BaseResponse deleteUser(@Parameter(description = SecurityUserRestInformation.SECURITY_USER_ID_PARAM_SUMMARY, required = true) @PathParam(
            AuthPath.PARAM_ID) String securityUserId) throws BaseException;
}
