package hu.evocelot.auth.service.auth.action.user;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.partner._1_0.rest.partner.PartnerEntityCoreType;
import hu.evocelot.auth.api.securityuser._1_0.rest.security_user.SecurityUserEntityCoreType;
import hu.evocelot.auth.api.user._1_0.rest.user.CreateUserRequest;
import hu.evocelot.auth.api.user._1_0.rest.user.UserResponse;
import hu.evocelot.auth.api.user._1_0.rest.user.UserType;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityTypeConverter;
import hu.evocelot.auth.service.auth.helper.PasswordHelper;
import hu.evocelot.auth.service.auth.service.PartnerService;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.model.base.generator.EntityIdGenerator;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for creating users (security user + partner).
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class CreateUserAction extends BaseAction {

    @Inject
    private SecurityUserEntityCoreTypeConverter securityUserEntityCoreTypeConverter;

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    @Inject
    private PartnerEntityCoreTypeConverter partnerEntityCoreTypeConverter;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private PartnerService partnerService;

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private TransactionHelper transactionHelper;

    @Inject
    private PasswordHelper passwordHelper;

    /**
     * For creating users (security user + partner).
     *
     * @param createUserRequest
     *         - the request that contains information about the user to create.
     * @return - with {@link UserResponse} that contains information about the created user.
     * @throws BaseException
     *         - when an error occurs.
     */
    public UserResponse createUser(CreateUserRequest createUserRequest) throws BaseException {
        // Check the request.
        if (Objects.isNull(createUserRequest)) {
            throw new InvalidParameterException("The createUserRequest is null!");
        }

        SecurityUserEntityCoreType securityUserDetails = createUserRequest.getSecurityUser();
        if (Objects.isNull(securityUserDetails)) {
            throw new InvalidParameterException("The securityUserDetails is null!");
        }

        PartnerEntityCoreType partnerDetails = createUserRequest.getPartner();
        if (Objects.isNull(partnerDetails)) {
            throw new InvalidParameterException("The partnerDetails is null!");
        }

        // Check the security group.
        SecurityGroup securityGroup = securityGroupService.findById(createUserRequest.getSecurityUser().getSecurityGroupId(), SecurityGroup.class);

        // Create the security user.
        SecurityUser securityUser = securityUserEntityCoreTypeConverter.convert(securityUserDetails);
        securityUser.setEmailAddress(createUserRequest.getEmailAddress());

        if (securityUserService.emailAddressAlreadyInUse(securityUser.getEmailAddress())) {
            throw new BusinessException(
                    FaultType.EMAIL_ALREADY_IN_USE,
                    MessageFormat.format("The provided email address [{0}] is already in use!", securityUser.getEmailAddress()));
        }

        String securityUserId = EntityIdGenerator.generateId();
        securityUser.setId(securityUserId);
        securityUser.setSecurityGroup(securityGroup);

        // TODO: Generates the password and send the mail instead of set the password via API.
        securityUser.setPasswordHash(passwordHelper.encryptPassword(createUserRequest.getPasswordHash(), securityUserId));
        SecurityUser finalSecurityUserEntity = securityUser;
        securityUser = transactionHelper.executeWithTransaction(() -> securityUserService.save(finalSecurityUserEntity));

        // Create the partner.
        Partner partner = partnerEntityCoreTypeConverter.convert(partnerDetails);
        partner.setSecurityUser(securityUser);
        Partner finalPartnerEntity = partner;
        partner = transactionHelper.executeWithTransaction(() -> partnerService.save(finalPartnerEntity));

        // Create the response.
        UserResponse response = new UserResponse();

        UserType userType = new UserType();
        userType.setSecurityUser(securityUserEntityTypeConverter.convert(securityUser));
        userType.setPartner(partnerEntityTypeConverter.convert(partner));
        response.setUserDetails(userType);

        handleSuccessResultType(response, createUserRequest);

        return response;
    }
}
