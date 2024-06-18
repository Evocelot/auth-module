package hu.evocelot.auth.service.auth.action.securitygroup;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupEntityCoreType;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for creating security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class CreateSecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupEntityCoreTypeConverter securityGroupEntityCoreTypeConverter;

    @Inject
    private SecurityGroupEntityTypeConverter securityGroupEntityTypeConverter;

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For creating security groups.
     *
     * @param securityGroupRequest
     *         - the request that contains information about the security group to create.
     * @return - with {@link SecurityGroupResponse} that contains information about the details of the created security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityGroupResponse createSecurityGroup(SecurityGroupRequest securityGroupRequest) throws BaseException {
        if (Objects.isNull(securityGroupRequest)) {
            throw new InvalidParameterException("The securityGroupRequest is null!");
        }

        SecurityGroupEntityCoreType securityGroupDetails = securityGroupRequest.getSecurityGroup();
        if (securityGroupService.nameAlreadyInUse(securityGroupDetails.getName())) {
            throw new BusinessException(
                    FaultType.SECURITY_GROUP_NAME_ALREADY_IN_USE,
                    MessageFormat.format("The name [{0}] of the security group is in use!", securityGroupDetails.getName()));
        }

        SecurityGroup securityGroup = securityGroupEntityCoreTypeConverter.convert(securityGroupDetails);

        SecurityGroup finalEntity = securityGroup;
        securityGroup = transactionHelper.executeWithTransaction(() -> securityGroupService.save(finalEntity));

        SecurityGroupResponse response = new SecurityGroupResponse();
        response.setSecurityGroup(securityGroupEntityTypeConverter.convert(securityGroup));
        handleSuccessResultType(response, securityGroupRequest);

        return response;
    }
}
