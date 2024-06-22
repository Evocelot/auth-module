package hu.evocelot.auth.service.auth.action.partner;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerResponse;
import hu.evocelot.auth.api.partner._1_0.rest.partner.UpdatePartnerRequest;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.Token;
import hu.evocelot.auth.model.enums.TokenType;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.auth.service.auth.service.TokenService;
import hu.icellmobilsoft.coffee.dto.exception.BONotFoundException;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for updating current (logged) partners.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateCurrentPartnerAction extends BaseAction {

    @Inject
    private UpdatePartnerAction updatePartnerAction;

    @Inject
    private ProjectHeader projectHeader;

    @Inject
    private TokenService tokenService;

    @Inject
    private PartnerService partnerService;

    /**
     * For updating the current (logged) partner.
     *
     * @param updatePartnerRequest
     *         - the request that contains the updated details of the partner.
     * @return - with {@link PartnerResponse} that contains information about the updated partner.
     * @throws BaseException
     *         - when an error occurs.
     */
    public PartnerResponse updateCurrentPartner(UpdatePartnerRequest updatePartnerRequest) throws BaseException {
        if (Objects.isNull(updatePartnerRequest)) {
            throw new InvalidParameterException("the updatePartnerRequest is null!");
        }

        String accessTokenValue = projectHeader.getSessionToken();
        if (StringUtils.isBlank(accessTokenValue)) {
            throw new BusinessException(FaultType.ACCESS_TOKEN_NOT_PRESENT, "The access token not present in the header.");
        }

        Token accessTokenEntity;
        try {
            accessTokenEntity = tokenService.findByValue(accessTokenValue, TokenType.ACCESS_TOKEN);
        } catch (BONotFoundException e) {
            throw new BusinessException(FaultType.NOT_LOGGED_IN,
                    MessageFormat.format("The user with access token [{0}] not logged in!", accessTokenValue));
        }

        Partner partner = partnerService.findBySecurityUserId(accessTokenEntity.getSecurityUser().getId());

        return updatePartnerAction.updatePartner(partner.getId(), updatePartnerRequest);
    }
}
