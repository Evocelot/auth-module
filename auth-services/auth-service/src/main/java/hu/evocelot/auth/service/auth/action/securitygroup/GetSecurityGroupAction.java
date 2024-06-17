package hu.evocelot.auth.service.auth.action.securitygroup;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Action class for getting security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class GetSecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupEntityTypeConverter securityGroupEntityTypeConverter;

    @Inject
    private SecurityGroupService securityGroupService;

    /**
     * For getting {@link SecurityGroup}.
     *
     * @param securityGroupId
     *         - the id of the security group to get.
     * @return - with {@link SecurityGroupResponse} that contains information about the security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityGroupResponse getSecurityGroup(String securityGroupId) throws BaseException {
        if (StringUtils.isBlank(securityGroupId)) {
            throw new InvalidParameterException("The securityGroupId is blank!");
        }

        SecurityGroup securityGroup = securityGroupService.findById(securityGroupId, SecurityGroup.class);

        SecurityGroupResponse response = new SecurityGroupResponse();
        response.setSecurityGroup(securityGroupEntityTypeConverter.convert(securityGroup));
        handleSuccessResultType(response);

        return response;
    }
}
