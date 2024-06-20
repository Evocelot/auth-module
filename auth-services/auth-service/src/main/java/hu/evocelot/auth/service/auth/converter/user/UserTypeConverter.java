package hu.evocelot.auth.service.auth.converter.user;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.user._1_0.rest.user.UserType;
import hu.evocelot.auth.model.Partner;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.model.SecurityUser;
import hu.evocelot.auth.service.auth.converter.partner.PartnerEntityTypeConverter;
import hu.evocelot.auth.service.auth.converter.securityuser.SecurityUserEntityTypeConverter;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Converter class for creating {@link UserType}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UserTypeConverter {

    @Inject
    private SecurityUserEntityTypeConverter securityUserEntityTypeConverter;

    @Inject
    private PartnerEntityTypeConverter partnerEntityTypeConverter;

    /**
     * Creates the {@link UserType} based on the security user and the partner.
     *
     * @param securityUser
     *         - the security user.
     * @param partner
     *         - the partner.
     * @return - with {@link UserType}.
     * @throws BaseException
     *         - when an error occurs.
     */
    public UserType createUserType(SecurityUser securityUser, Partner partner, SecurityGroup securityGroup) throws BaseException {
        UserType userType = new UserType();

        userType.setSecurityUser(securityUserEntityTypeConverter.convert(securityUser));
        userType.setPartner(partnerEntityTypeConverter.convert(partner));
        userType.setSecurityGroupName(securityGroup.getName());

        return userType;
    }
}
