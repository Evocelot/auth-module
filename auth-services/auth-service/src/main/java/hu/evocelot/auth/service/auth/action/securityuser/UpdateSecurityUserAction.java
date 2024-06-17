package hu.evocelot.auth.service.auth.action.securityuser;

import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserEntityCoreType;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserResponse;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.UpdateSecurityUserRequest;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.converter.SecurityUserEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.SecurityUserEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for updating security users.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateSecurityUserAction extends BaseAction {

    @Inject
    private SecurityUserEntityCoreTypeConverter securityUserEntityCoreTypeConverter;

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For updating security users.
     *
     * @param securityUserId
     *         - the id of the security user to update.
     * @param updateSecurityUserRequest
     *         - the request that contains information about the updated security user.
     * @return - with {@link SecurityUserResponse} that contains the information about the updated security user.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityUserResponse updateSecurityUser(String securityUserId, UpdateSecurityUserRequest updateSecurityUserRequest) throws BaseException {
        if (StringUtils.isBlank(securityUserId)) {
            throw new InvalidParameterException("The securityUserId is blank!");
        } else if (Objects.isNull(updateSecurityUserRequest)) {
            throw new InvalidParameterException("The updateSecurityUserRequest is null!");
        }

        SecurityUserEntityCoreType updatedDetails = updateSecurityUserRequest.getSecurityUser();
        if (Objects.isNull(updatedDetails)) {
            throw new InvalidParameterException("The updateSecurityUserRequest.getSecurityUser is null!");
        }

        SecurityUser securityUser = securityUserService.findById(securityUserId, SecurityUser.class);
        securityUserEntityCoreTypeConverter.convert(securityUser, updatedDetails);

        SecurityGroup securityGroup = securityGroupService.findById(updatedDetails.getSecurityGroupId(), SecurityGroup.class);
        securityUser.setSecurityGroup(securityGroup);

        SecurityUser finalEntity = securityUser;
        securityUser = transactionHelper.executeWithTransaction(() -> securityUserService.save(finalEntity));

        SecurityUserResponse response = new SecurityUserResponse();
        response.setSecurityUser(securityUserEntityTypeConverter.convert(securityUser));

        handleSuccessResultType(response, updateSecurityUserRequest);

        return response;
    }
}
