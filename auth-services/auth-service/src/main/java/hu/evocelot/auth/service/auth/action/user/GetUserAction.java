package hu.evocelot.auth.service.auth.action.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for getting users (security user + partner).
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetUserAction extends BaseAction {

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    @Inject
    private PartnerService partnerService;

    /**
     * For getting the users.
     *
     * @param securityUserId
     *         - the id of the security user (the owner of the partner).
     * @return - with {@link UserResponse} that contains information about the user.
     * @throws BaseException
     *         - when an error occurs.
     */
    public UserResponse getUser(String securityUserId) throws BaseException {
        if (StringUtils.isBlank(securityUserId)) {
            throw new InvalidParameterException("The securityUserId is blank!");
        }

        Partner partner = partnerService.findBySecurityUserIdFetchSecurityUser(securityUserId);

        UserResponse response = new UserResponse();

        response.setPartner(partnerEntityTypeConverter.convert(partner));
        response.setSecurityUser(securityUserEntityTypeConverter.convert(partner.getSecurityUser()));

        handleSuccessResultType(response);
        return response;
    }
}
