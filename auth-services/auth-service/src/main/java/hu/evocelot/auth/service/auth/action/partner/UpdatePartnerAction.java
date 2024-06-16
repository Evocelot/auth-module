package hu.evocelot.auth.service.auth.action.partner;

import jakarta.enterprise.context.ApplicationScoped;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.partner._1_0.rest.partner.UpdatePartnerRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for updating partners.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdatePartnerAction extends BaseAction {

    public PartnerResponse updatePartner(String partnerId, UpdatePartnerRequest updatePartnerRequest) throws BaseException {
        return null;
    }
}
