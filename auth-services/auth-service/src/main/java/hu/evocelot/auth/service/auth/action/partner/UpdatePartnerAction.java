package hu.evocelot.auth.service.auth.action.partner;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerEntityCoreType;
import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.partner._1_0.rest.partner.UpdatePartnerRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for updating partners.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdatePartnerAction extends BaseAction {

    @Inject
    private PartnerEntityCoreTypeConverter partnerEntityCoreTypeConverter;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private PartnerService partnerService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For updating partners.
     *
     * @param partnerId
     *         - the id of the partner to update.
     * @param updatePartnerRequest
     *         - the request that contains information about the updated partner.
     * @return - with {@link PartnerResponse} that contains information about the updated partner.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PartnerResponse updatePartner(String partnerId, UpdatePartnerRequest updatePartnerRequest) throws BaseException {
        if (StringUtils.isBlank(partnerId)) {
            throw new InvalidParameterException("The partnerId is null!");
        } else if (Objects.isNull(updatePartnerRequest)) {
            throw new InvalidParameterException("The updatePartnerRequest is null!");
        }

        PartnerEntityCoreType updatedPartnerDetails = updatePartnerRequest.getPartner();
        if (Objects.isNull(updatedPartnerDetails)) {
            throw new InvalidParameterException("The updatePartnerRequest.getPartner is null!");
        }

        Partner partner = partnerService.findById(partnerId, Partner.class);

        partnerEntityCoreTypeConverter.convert(partner, updatedPartnerDetails);

        Partner finalEntity = partner;
        partner = transactionHelper.executeWithTransaction(() -> partnerService.save(finalEntity));

        PartnerResponse response = new PartnerResponse();
        response.setPartner(partnerEntityTypeConverter.convert(partner));

        handleSuccessResultType(response, updatePartnerRequest);

        return response;
    }
}
