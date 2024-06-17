package hu.evocelot.auth.service.auth.action.partner;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for downloading profile picture.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class DownloadProfilePictureAction extends BaseAction {

    public Response downloadProfilePicture(String partnerId) throws BaseException {
        return null;
    }
}
