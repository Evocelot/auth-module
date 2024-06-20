package hu.evocelot.auth.service.auth.action.securitygroup;

import java.text.MessageFormat;
import java.util.Objects;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupEntityCoreType;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupRequest;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupResponse;
import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityCoreTypeConverter;
import hu.evocelot.auth.service.auth.converter.securitygroup.SecurityGroupEntityTypeConverter;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.jpa.helper.TransactionHelper;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for updating security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class UpdateSecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupEntityCoreTypeConverter securityGroupEntityCoreTypeConverter;

    @Inject
    private SecurityGroupEntityTypeConverter securityGroupEntityTypeConverter;

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private RedisHelper redisHelper;

    @Inject
    private TransactionHelper transactionHelper;

    /**
     * For updating the security groups.
     *
     * @param securityGroupId
     *         - the id of the security group to update.
     * @param securityGroupRequest
     *         - the request that contains information about the updated details of the security group.
     * @return - with {@link SecurityGroupResponse} that contains information about the updated details of the security group.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityGroupResponse updateSecurityGroup(String securityGroupId, SecurityGroupRequest securityGroupRequest) throws BaseException {
        if (StringUtils.isBlank(securityGroupId)) {
            throw new InvalidParameterException("The securityGroupId is blank!");
        } else if (Objects.isNull(securityGroupRequest)) {
            throw new InvalidParameterException("The securityGroupRequest is null!");
        }

        SecurityGroupEntityCoreType updatedSecurityGroupDetails = securityGroupRequest.getSecurityGroup();
        if (Objects.isNull(updatedSecurityGroupDetails)) {
            throw new InvalidParameterException("The securityGroupRequest.getSecurityGroup is null!");
        }

        SecurityGroup securityGroup = securityGroupService.findById(securityGroupId, SecurityGroup.class);
        String newSecurityGroupName = updatedSecurityGroupDetails.getName();
        if (!newSecurityGroupName.equals(securityGroup.getName())) {
            if (securityGroupService.nameAlreadyInUse(newSecurityGroupName)) {
                throw new BusinessException(
                        FaultType.SECURITY_GROUP_NAME_ALREADY_IN_USE,
                        MessageFormat.format("The new name [{0}] of the security group is in use!", newSecurityGroupName));
            }
        }

        securityGroupEntityCoreTypeConverter.convert(securityGroup, updatedSecurityGroupDetails);

        SecurityGroup finalEntity = securityGroup;
        securityGroup = transactionHelper.executeWithTransaction(() -> securityGroupService.save(finalEntity));

        redisHelper.endSecurityGroupSessions(securityGroup.getId());

        SecurityGroupResponse response = new SecurityGroupResponse();
        response.setSecurityGroup(securityGroupEntityTypeConverter.convert(securityGroup));
        handleSuccessResultType(response, securityGroupRequest);

        return response;
    }
}
