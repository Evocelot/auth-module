package hu.evocelot.auth.service.auth.helper;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import hu.evocelot.auth.api.permission._1_0.rest.permission.PermissionEntityType;
import hu.evocelot.auth.api.securitygroup._1_0.rest.security_group.SecurityGroupWithPermissionsType;
import hu.evocelot.auth.model.PermissionToSecurityGroup;
import hu.evocelot.auth.model.SecurityGroup;
import hu.evocelot.auth.service.auth.converter.permission.PermissionEntityTypeConverter;
import hu.evocelot.auth.service.auth.service.PermissionToSecurityGroupService;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;

/**
 * Helper class for handling {@link SecurityGroup}.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class SecurityGroupHelper {

    @Inject
    private PermissionToSecurityGroupService permissionToSecurityGroupService;

    @Inject
    private PermissionEntityTypeConverter permissionEntityTypeConverter;

    /**
     * For getting the security group with the permission list.
     *
     * @param securityGroup
     *         - the security group.
     * @return - with {@link SecurityGroupWithPermissionsType} that contains the details of the security group and the permissions too.
     * @throws BaseException
     *         - when an error occurs.
     */
    public SecurityGroupWithPermissionsType getSecurityGroupWithPermissions(SecurityGroup securityGroup) throws BaseException {
        SecurityGroupWithPermissionsType response = new SecurityGroupWithPermissionsType();

        response.setSecurityGroupId(securityGroup.getId());
        response.setName(securityGroup.getName());
        response.setDescription(securityGroup.getDescription());

        List<PermissionEntityType> permissionTypes = new ArrayList<>();

        for (PermissionToSecurityGroup permissionToSecurityGroup : permissionToSecurityGroupService.findBySecurityGroupIdFetchPermission(securityGroup.getId())) {
            permissionTypes.add(permissionEntityTypeConverter.convert(permissionToSecurityGroup.getPermission()));
        }

        response.withPermissions(permissionTypes);

        return response;
    }
}
