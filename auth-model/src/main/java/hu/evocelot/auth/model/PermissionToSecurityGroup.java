package hu.evocelot.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * The PermissionToSecurityGroup entity.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Entity
@Table(name = "permission_to_security_group")
public class PermissionToSecurityGroup extends AbstractIdentifiedAuditEntity {

    /**
     * The security group.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_group_id")
    private SecurityGroup securityGroup;

    /**
     * The permission.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private Permission permission;

    /**
     * For getting the securityGroup.
     *
     * @return - with the securityGroup.
     */
    public SecurityGroup getSecurityGroup() {
        return securityGroup;
    }

    /**
     * For setting the securityGroup.
     *
     * @param securityGroup
     *         - the securityGroup to set.
     */
    public void setSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroup = securityGroup;
    }

    /**
     * For getting the permission.
     *
     * @return - with the permission.
     */
    public Permission getPermission() {
        return permission;
    }

    /**
     * For setting the permission.
     *
     * @param permission
     *         - the permission to set.
     */
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
