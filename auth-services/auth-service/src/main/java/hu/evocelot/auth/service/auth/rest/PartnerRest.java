package hu.evocelot.auth.service.auth.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.partner._1_0.rest.partner.UpdatePartnerRequest;
import hu.evocelot.auth.api.rest.jee10.IPartnerRest;
import hu.evocelot.auth.api.rest.jee10.dto.UploadProfilePictureRequest;
import hu.evocelot.auth.common.system.rest.rest.BaseRestService;
import hu.evocelot.auth.service.auth.action.partner.DownloadProfilePictureAction;
import hu.evocelot.auth.service.auth.action.partner.GetPartnerAction;
import hu.evocelot.auth.service.auth.action.partner.UpdateCurrentPartnerAction;
import hu.evocelot.auth.service.auth.action.partner.UpdatePartnerAction;
import hu.evocelot.auth.service.auth.action.partner.UploadProfilePictureAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Rest class for implementing the {@link IPartnerRest}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class PartnerRest extends BaseRestService implements IPartnerRest {

    @Inject
    private GetPartnerAction getPartnerAction;

    @Inject
    private UpdatePartnerAction updatePartnerAction;

    @Inject
    private UpdateCurrentPartnerAction updateCurrentPartnerAction;

    @Inject
    private UploadProfilePictureAction uploadProfilePictureAction;

    @Inject
    private DownloadProfilePictureAction downloadProfilePictureAction;

    @Override
    public PartnerResponse getPartner(String partnerId) throws BaseException {
        return wrapPathParam1(getPartnerAction::getPartner, partnerId, "getPartner", "partnerId");
    }

    @Override
    public PartnerResponse updatePartner(String partnerId, UpdatePartnerRequest updatePartnerRequest) throws BaseException {
        return wrapPathParam2(updatePartnerAction::updatePartner,
                partnerId,
                updatePartnerRequest,
                "updatePartner",
                "partnerId",
                "updatePartnerRequest");
    }

    @Override
    public PartnerResponse updateCurrentPartner(UpdatePartnerRequest updatePartnerRequest) throws BaseException {
        return wrapPathParam1(updateCurrentPartnerAction::updateCurrentPartner, updatePartnerRequest, "updateCurrentPartner", "updatePartnerRequest");
    }

    @Override
    public PartnerResponse uploadProfilePicture(String partnerId, UploadProfilePictureRequest uploadProfilePictureRequest) throws BaseException {
        return wrapPathParam2(uploadProfilePictureAction::uploadProfilePicture,
                partnerId,
                uploadProfilePictureRequest,
                "uploadProfilePicture",
                "partnerId",
                "uploadProfilePictureRequest");
    }

    @Override
    public Response downloadProfilePicture(String partnerId) throws BaseException {
        return wrapPathParam1(downloadProfilePictureAction::downloadProfilePicture, partnerId, "downloadProfilePicture", "partnerId");
    }
}
