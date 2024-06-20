package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.SecurityUserRestInformation;
import hu.evocelot.auth.api.rest.jee10.constant.SecuritySchemeConstant;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.ChangeCurrentPasswordRequest;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for security user management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = SecurityUserRestInformation.TAG, description = SecurityUserRestInformation.DESCRIPTION)
@Path(AuthPath.SECURITY_USER_MANAGEMENT)
public interface ISecurityUserRest {

    /**
     * HTTP PUT method for updating security users.
     *
     * @param securityUserId
     *         - the id of the security user to update.
     * @param updateSecurityUserRequest
     *         - the request that contains the updated details of the security user.
     * @return - with {@link SecurityUserResponse} that contains the updated security user details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = SecurityUserRestInformation.UPDATE_SECURITY_USER_SUMMARY,
            description = SecurityUserRestInformation.UPDATE_SECURITY_USER_DESCRIPTION)
    @SecurityRequirement(name = SecuritySchemeConstant.HEADER_ACCESS_TOKEN)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(BaseServicePath.ID)
    SecurityUserResponse updateSecurityUser(
            @Parameter(description = SecurityUserRestInformation.SECURITY_USER_ID_PARAM_SUMMARY, required = true) @PathParam(
                    BaseServicePath.PARAM_ID) String securityUserId,
            @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException;

    /**
     * HTTP PUT method for updating the current (logged) security users.
     *
     * @param updateSecurityUserRequest
     *         - the request that contains the updated details of the security user.
     * @return - with {@link SecurityUserResponse} that contains the updated security user details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = SecurityUserRestInformation.UPDATE_CURRENT_SECURITY_USER_SUMMARY,
            description = SecurityUserRestInformation.UPDATE_CURRENT_SECURITY_USER_DESCRIPTION)
    @SecurityRequirement(name = SecuritySchemeConstant.HEADER_ACCESS_TOKEN)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.CURRENT)
    SecurityUserResponse updateCurrentSecurityUser(
            @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException;

    /**
     * HTTP PUT method for changing the current (logged) security users password.
     *
     * @param changeCurrentPasswordRequest
     *         - the request that contains the actual and the new password hash.
     * @return - with {@link SecurityUserResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = SecurityUserRestInformation.CHANGE_CURRENT_PASSWORD_SUMMARY,
            description = SecurityUserRestInformation.CHANGE_CURRENT_PASSWORD_DESCRIPTION)
    @SecurityRequirement(name = SecuritySchemeConstant.HEADER_ACCESS_TOKEN)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.CURRENT + AuthPath.CHANGE_PASSWORD)
    SecurityUserResponse changeCurrentPassword(
            @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) ChangeCurrentPasswordRequest changeCurrentPasswordRequest) throws BaseException;
}
