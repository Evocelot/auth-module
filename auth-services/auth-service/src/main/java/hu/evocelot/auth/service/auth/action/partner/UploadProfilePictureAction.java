package hu.evocelot.auth.service.auth.action.partner;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.rest.jee10.dto.UploadProfilePictureRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.file.api.file._1_0.rest.document.DocumentResponse;
import hu.evocelot.file.api.rest.jee10.client.IFileServiceRestClient;
import hu.evocelot.file.api.rest.jee10.dto.UploadFileRequest;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for uploading profile picture to the partner.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UploadProfilePictureAction extends BaseAction {

    @Inject
    @RestClient
    private IFileServiceRestClient fileServiceRestClient;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private PartnerService partnerService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For uploading the new profile picture. The original profile picture will be deleted.
     *
     * @param partnerId
     *         - the id of the partner. The owner of the profile picture.
     * @param uploadProfilePictureRequest
     *         - the request that contains information about the partner and the profile picture too.
     * @return - with {@link PartnerResponse} that contains the updated details of the partner.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PartnerResponse uploadProfilePicture(String partnerId, UploadProfilePictureRequest uploadProfilePictureRequest) throws BaseException {
        if (StringUtils.isBlank(partnerId)) {
            throw new InvalidParameterException("The partnerId is null!");
        } else if (Objects.isNull(uploadProfilePictureRequest)) {
            throw new InvalidParameterException("The uploadProfilePictureRequest is null!");
        }

        Partner partner = partnerService.findById(partnerId, Partner.class);

        // Delete the original file.
        if (StringUtils.isNoneBlank(partner.getProfilePictureId())) {
            fileServiceRestClient.deleteFile(partner.getProfilePictureId());
        }

        // Upload the new profile picture.
        DocumentResponse documentResponse = fileServiceRestClient.uploadFile(createUploadFileRequest(partner, uploadProfilePictureRequest));
        partner.setProfilePictureId(documentResponse.getDocument().getDocumentId());

        Partner finalEntity = partner;
        partner = transactionHelper.executeWithTransaction(() -> partnerService.save(finalEntity));

        PartnerResponse response = new PartnerResponse();
        response.setPartner(partnerEntityTypeConverter.convert(partner));
        handleSuccessResultType(response, uploadProfilePictureRequest);

        return response;
    }

    private UploadFileRequest createUploadFileRequest(Partner partner, UploadProfilePictureRequest uploadProfilePictureRequest) {
        UploadFileRequest request = new UploadFileRequest();

        request.setFileInputStream(uploadProfilePictureRequest.getFileInputStream());
        request.setExtension(uploadProfilePictureRequest.getExtension());
        request.setName("profile-picture");
        request.setNumber(1);
        request.setObjectId(partner.getId());
        request.setSystemId("auth-service");

        return request;
    }
}
