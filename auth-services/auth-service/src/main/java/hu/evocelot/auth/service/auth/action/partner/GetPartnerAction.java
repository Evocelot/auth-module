package hu.evocelot.auth.service.auth.action.partner;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for getting partners.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetPartnerAction extends BaseAction {

    public PartnerResponse getPartner(String partnerId) throws BaseException {
        return null;
    }
}
