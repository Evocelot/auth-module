package hu.evocelot.auth.api.rest.jee10;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import hu.evocelot.auth.api.common.path.AuthPath;
import hu.evocelot.auth.api.common.restinformation.PartnerRestInformation;
import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.partner._1_0.rest.partner.UpdatePartnerRequest;
import hu.evocelot.auth.api.rest.jee10.dto.UploadProfilePictureRequest;
import hu.evocelot.auth.dto.constant.XsdConstants;
import hu.icellmobilsoft.coffee.cdi.annotation.xml.ValidateXML;
import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * REST endpoint for partner management
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Tag(name = PartnerRestInformation.TAG, description = PartnerRestInformation.DESCRIPTION)
@Path(AuthPath.PARTNER_SERVICE)
public interface IPartnerRest {

    /**
     * HTTP PUT method for updating partners.
     *
     * @param partnerId
     *         - the id of the partner.
     * @param updatePartnerRequest
     *         - the request that contains the updated details of the partner.
     * @return - with {@link PartnerResponse} that contains the updated partner details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = PartnerRestInformation.UPDATE_PARTNER_SUMMARY, description = PartnerRestInformation.UPDATE_PARTNER_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(BaseServicePath.ID)
    PartnerResponse updatePartner(@Parameter(description = PartnerRestInformation.PARTNER_ID_PARAM_SUMMARY, required = true) @PathParam(
            BaseServicePath.PARAM_ID) String partnerId, @ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) UpdatePartnerRequest updatePartnerRequest)
            throws BaseException;

    /**
     * HTTP PUT method for updating the current (logged) partner.
     *
     * @param updatePartnerRequest
     *         - the request that contains the updated details of the partner.
     * @return - with {@link PartnerResponse} that contains the updated partner details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @PUT
    @Operation(summary = PartnerRestInformation.UPDATE_CURRENT_PARTNER_SUMMARY,
            description = PartnerRestInformation.UPDATE_CURRENT_PARTNER_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Path(AuthPath.CURRENT)
    PartnerResponse updateCurrentPartner(@ValidateXML(xsdPath = XsdConstants.SUPER_XSD_PATH) UpdatePartnerRequest updatePartnerRequest)
            throws BaseException;

    /**
     * HTTP POST method for uploading profile pictures.
     *
     * @param partnerId
     *         - the owner of the profile picture.
     * @param uploadProfilePictureRequest
     *         - the request that contains the details of the profile picture.
     * @return - with {@link PartnerResponse} that contains the updated partner details.
     * @throws BaseException
     *         - when an error occurs.
     */
    @POST
    @Operation(summary = PartnerRestInformation.UPLOAD_PROFILE_PICTURE_SUMMARY,
            description = PartnerRestInformation.UPLOAD_PROFILE_PICTURE_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.APPLICATION_XML })
    @Consumes(value = { MediaType.MULTIPART_FORM_DATA })
    @Path(BaseServicePath.ID + AuthPath.PROFILE_PICTURE)
    PartnerResponse uploadProfilePicture(@Parameter(description = PartnerRestInformation.PARTNER_ID_PARAM_SUMMARY, required = true) @PathParam(
            BaseServicePath.PARAM_ID) String partnerId, @MultipartForm UploadProfilePictureRequest uploadProfilePictureRequest) throws BaseException;

    /**
     * HTTP GET method for download the profile picture.
     *
     * @param partnerId
     *         - the id of the partner.
     * @return - with {@link Response} that contains the profile picture.
     * @throws BaseException
     *         - when an error occurs.
     */
    @GET
    @Operation(summary = PartnerRestInformation.DOWNLOAD_PROFILE_PICTURE_SUMMARY,
            description = PartnerRestInformation.DOWNLOAD_PROFILE_PICTURE_DESCRIPTION)
    @Produces(value = { MediaType.APPLICATION_OCTET_STREAM })
    @Path(BaseServicePath.ID + AuthPath.PROFILE_PICTURE)
    Response downloadProfilePicture(@Parameter(description = PartnerRestInformation.PARTNER_ID_PARAM_SUMMARY, required = true) @PathParam(
            BaseServicePath.PARAM_ID) String partnerId) throws BaseException;
}
