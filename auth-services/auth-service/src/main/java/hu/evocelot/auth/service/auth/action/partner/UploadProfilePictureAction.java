package hu.evocelot.auth.service.auth.action.partner;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.rest.jee10.dto.UploadProfilePictureRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for uploading profile picture to the partner.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UploadProfilePictureAction extends BaseAction {

    public PartnerResponse uploadProfilePicture(String partnerId, UploadProfilePictureRequest uploadProfilePictureRequest) throws BaseException {
        return null;
    }
}
