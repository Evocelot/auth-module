package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginRequest;
import hu.evocelot.auth.api.auth._1_0.rest.auth.LoginResponse;
import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.AuthRestInformation;
import hu.evocelot.auth.api.rest.jee10.constant.SecuritySchemeConstant;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for auth (login-logout) management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = AuthRestInformation.TAG, description = AuthRestInformation.DESCRIPTION)
@Path(AuthPath.AUTH_MANAGEMENT)
public interface IAuthRest {

    /**
     * HTTP POST method that logs in a user by validating the login request and issuing access and refresh tokens.
     *
     * @param loginRequest
     *         - the request containing login details.
     * @return - with {@link LoginResponse} that contains the details of the current user and the tokens.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = AuthRestInformation.LOGIN_SUMMARY, description = AuthRestInformation.LOGIN_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.LOGIN)
    LoginResponse login(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) LoginRequest loginRequest) throws BaseException;

    /**
     * HTTP POST method that logs out a user by invalidating the current access token.
     *
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = AuthRestInformation.LOGOUT_SUMMARY, description = AuthRestInformation.LOGOUT_DESCRIPTION)
    @SecurityRequirement(name = SecuritySchemeConstant.HEADER_ACCESS_TOKEN)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.LOGOUT)
    BaseResponse logout() throws BaseException;

    /**
     * HTTP POST method that generates a new access token using the provided refresh token. The original access token will be invalidated.
     *
     * @param refreshToken
     *         - the value of the refresh token.
     * @return - with {@link LoginResponse} that contains the details of the new access token.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = AuthRestInformation.GET_NEW_ACCESS_TOKEN_SUMMARY, description = AuthRestInformation.GET_NEW_ACCESS_TOKEN_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.REFRESH_ACCESS + AuthPath.TOKEN_VALUE)
    LoginResponse getNewAccessToken(@Parameter(description = AuthRestInformation.REFRESH_TOKEN_PARAM_DESCRIPTION, required = true) @PathParam(
            AuthPath.TOKEN_VALUE_PARAM) String refreshToken) throws BaseException;
}
