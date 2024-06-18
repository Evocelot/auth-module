package hu.evocelot.auth.service.auth.action.partner;

import java.io.InputStream;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.file.api.rest.jee10.client.IFileServiceRestClient;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.rest.utils.ResponseUtil;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for downloading profile picture.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class DownloadProfilePictureAction extends BaseAction {

    private static final String PATH_OF_THE_DEFAULT_PROFILE_PICTURE = "default-profile-picture.jpg";

    @Inject
    @RestClient
    private IFileServiceRestClient fileServiceRestClient;

    @Inject
    private PartnerService partnerService;

    /**
     * For downloading the profile picture.
     *
     * @param partnerId
     *         - the id of the partner.
     * @return - with {@link Response} that contains the profile picture. If there is no profile picture, then the default profile picture will be
     *         stored in the response.
     * @throws BaseException
     *         - when an error occurs.
     */
    public Response downloadProfilePicture(String partnerId) throws BaseException {
        if (StringUtils.isBlank(partnerId)) {
            throw new InvalidParameterException("The partnerId is null!");
        }

        Partner partner;

        try {
            partner = partnerService.findById(partnerId, Partner.class);
        } catch (BONotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Partner not found!").type(MediaType.TEXT_PLAIN).build();
        }

        if (StringUtils.isBlank(partner.getProfilePictureId())) {
            return getDefaultProfilePicture();
        }

        return fileServiceRestClient.downloadFile(partner.getProfilePictureId());
    }

    private Response getDefaultProfilePicture() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PATH_OF_THE_DEFAULT_PROFILE_PICTURE);
        if (inputStream == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Default profile picture not found!").type(MediaType.TEXT_PLAIN).build();
        }

        return ResponseUtil.getFileResponse(inputStream, "profile-picture.jpg", MediaType.APPLICATION_OCTET_STREAM);
    }
}
