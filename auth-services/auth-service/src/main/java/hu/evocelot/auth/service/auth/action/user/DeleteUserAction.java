package hu.evocelot.auth.service.auth.action.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for deleting users (security user + partner).
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class DeleteUserAction extends BaseAction {

    @Inject
    private PartnerService partnerService;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private RedisHelper redisHelper;

    /**
     * For deleting the user (security user and partner)
     *
     * @param securityUserId
     *         - the id of the security user.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @Transactional
    public BaseResponse deleteUser(String securityUserId) throws BaseException {
        if (StringUtils.isBlank(securityUserId)) {
            throw new InvalidParameterException("The securityUserId is blank!");
        }

        Partner partner = partnerService.findBySecurityUserIdFetchSecurityUser(securityUserId);

        partnerService.delete(partner);
        securityUserService.delete(partner.getSecurityUser());

        // TODO: expire the sessions in the db too.
        redisHelper.endUserSessions(partner.getSecurityUser().getId());

        BaseResponse response = new BaseResponse();
        handleSuccessResultType(response);
        return response;
    }
}
