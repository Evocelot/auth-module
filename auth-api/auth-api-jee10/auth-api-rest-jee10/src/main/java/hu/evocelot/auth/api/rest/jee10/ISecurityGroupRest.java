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

import hu.evocelot.auth.api.common._1_0.common.EntityIdRequest;
import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.SecurityGroupRestInformation;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryRequest;
import hu.evocelot.auth.api.securitygroupquery._1_0.rest.security_group_query.SecurityGroupQueryResponse;
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

    /**
     * HTTP POST method for adding permission to the security group.
     *
     * @param securityGroupId
     *         - the id of the security group to add the permission.
     * @param entityIdRequest
     *         - the request that contains the id of the permission to add.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = SecurityGroupRestInformation.ADD_PERMISSION_TO_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.ADD_PERMISSION_TO_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID + AuthPath.ADD_PERMISSION)
    BaseResponse addPermissionToSecurityGroup(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SecurityGroupRestInformation.SECURITY_GROUP_ID_PARAM_SUMMARY,
                    required = true) String securityGroupId, @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) EntityIdRequest entityIdRequest)
            throws BaseException;

    /**
     * HTTP DELETE method for deleting the permission from the security group.
     *
     * @param securityGroupId
     *         - the id of the security group from where remove the permission.
     * @param entityIdRequest
     *         - the request that contains the id of the permission to remove.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @DELETE
    @Operation(summary = SecurityGroupRestInformation.DELETE_PERMISSION_FROM_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.DELETE_PERMISSION_FROM_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.ID + AuthPath.DELETE_PERMISSION)
    BaseResponse removePermissionFromSecurityGroup(
            @PathParam(BaseServicePath.PARAM_ID) @Parameter(description = SecurityGroupRestInformation.SECURITY_GROUP_ID_PARAM_SUMMARY,
                    required = true) String securityGroupId, @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) EntityIdRequest entityIdRequest)
            throws BaseException;

    /**
     * HTTP POST method for listing security groups.
     *
     * @param securityGroupQueryRequest
     *         - the request that contains information about the filtering, sorting and paging details.
     * @return - with {@link SecurityGroupResponse} that contains the relevant security groups.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = SecurityGroupRestInformation.QUERY_SECURITY_GROUP_SUMMARY,
            description = SecurityGroupRestInformation.QUERY_SECURITY_GROUP_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_XML })
    @Path(BaseServicePath.QUERY)
    SecurityGroupQueryResponse querySecurityGroup(
            @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) SecurityGroupQueryRequest securityGroupQueryRequest) throws BaseException;
}
