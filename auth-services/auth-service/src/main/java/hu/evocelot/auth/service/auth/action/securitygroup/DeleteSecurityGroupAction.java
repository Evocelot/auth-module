package hu.evocelot.auth.service.auth.action.securitygroup;

import java.text.MessageFormat;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.common.system.rest.action.BaseAction;
import hu.evocelot.auth.dto.exception.enums.FaultType;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.helper.RedisHelper;
import hu.evocelot.auth.service.auth.service.SecurityGroupService;
import hu.evocelot.auth.service.auth.service.SecurityUserService;
import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseResponse;
import hu.icellmobilsoft.coffee.dto.exception.InvalidParameterException;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.icellmobilsoft.coffee.se.api.exception.BusinessException;

/**
 * Action class for deleting security groups.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class DeleteSecurityGroupAction extends BaseAction {

    @Inject
    private SecurityGroupService securityGroupService;

    @Inject
    private SecurityUserService securityUserService;

    @Inject
    private RedisHelper redisHelper;

    /**
     * For deleting security groups.
     *
     * @param securityGroupId
     *         - the id of the security group to delete.
     * @return - with {@link BaseResponse}.
     * @throws BaseException
     *         - when an error occurs.
     */
    @Transactional
    public BaseResponse deleteSecurityGroup(String securityGroupId) throws BaseException {
        if (StringUtils.isBlank(securityGroupId)) {
            throw new InvalidParameterException("The securityGroupId is blank!");
        }

        SecurityGroup securityGroup = securityGroupService.findById(securityGroupId, SecurityGroup.class);

        if (securityUserService.securityGroupIsUnderUse(securityGroup.getId())) {
            throw new BusinessException(FaultType.SECURITY_GROUP_IS_UNDER_USE, MessageFormat.format(
                    "Cannot delete the security group with id [{0}] because there is at least one security user that use the referenced group!",
                    securityGroup.getId()));
        }

        securityGroupService.delete(securityGroup);

        redisHelper.deleteKey(securityGroup.getId());

        BaseResponse response = new BaseResponse();
        handleSuccessResultType(response);

        return response;
    }
}
