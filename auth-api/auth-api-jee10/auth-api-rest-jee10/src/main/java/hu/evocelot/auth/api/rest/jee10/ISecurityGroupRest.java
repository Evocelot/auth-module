package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
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
import hu.evocelot.auth.api.common.restinformation.SecurityGroupRestInformation;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for security group management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = SecurityGroupRestInformation.TAG, description = SecurityGroupRestInformation.DESCRIPTION)
@Path(AuthPath.SECURITY_GROUP_SERVICE)
public interface ISecurityGroupRest {

    /**
     * HTTP POST method for creating new security groups.
     *
     * @param securityGroupRequest
     *         - the request that contains the information about the security group to create.
     * @return - with {@link SecurityGroupResponse} that contains the information about the created security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = SecurityGroupRestInformation.CREATE_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.CREATE_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    SecurityGroupResponse createSecurityGroup(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) SecurityGroupRequest securityGroupRequest)
            throws BaseException;

    /**
     * HTTP PUT method for updating the base details of the security group.
     *
     * @param securityGroupId
     *         - the id of the security group to update.
     * @param securityGroupRequest
     *         - the request that contains information about the updated security group.
     * @return - with {@link SecurityGroupResponse} that contains information about the updated security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = SecurityGroupRestInformation.UPDATE_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.UPDATE_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    SecurityGroupResponse updateSecurityGroup(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SecurityGroupRestInformation.SECURITY_GROUP_ID_PARAM_SUMMARY,
                    required = true) String securityGroupId,
            @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) SecurityGroupRequest securityGroupRequest) throws BaseException;

    /**
     * HTTP DELETE method for deleting security groups.
     *
     * @param securityGroupId
     *         - the id of the security group to delete.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @DELETE
    @Operation(summary = SecurityGroupRestInformation.DELETE_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.DELETE_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    BaseResponse deleteSecurityGroup(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SecurityGroupRestInformation.SECURITY_GROUP_ID_PARAM_SUMMARY,
                    required = true) String securityGroupId) throws BaseException;

    /**
     * HTTP GET method for getting security groups.
     *
     * @param securityGroupId
     *         - the id of the security group to get.
     * @return - with {@link SecurityGroupResponse} that contains information about the security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    @GET
    @Operation(summary = SecurityGroupRestInformation.GET_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.GET_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID)
    SecurityGroupResponse getSecurityGroup(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SecurityGroupRestInformation.SECURITY_GROUP_ID_PARAM_SUMMARY,
                    required = true) String securityGroupId) throws BaseException;
}
