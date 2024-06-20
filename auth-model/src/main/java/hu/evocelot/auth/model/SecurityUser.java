package hu.evocelot.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import hu.evocelot.auth.model.enums.SecurityUserStatus;
import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * The security_user entity.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Entity
@Table(name = "security_user")
public class SecurityUser extends AbstractIdentifiedAuditEntity {

    /**
     * The email address of the security user.
     */
    @Size(max = 320)
    @NotNull
    @Column(name = "email_address", length = 320, nullable = false, unique = true)
    private String emailAddress;

    /**
     * The hashed password of the security user. The X__ID used as the salt.
     */
    @Size(max = 255)
    @NotNull
    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    /**
     * The status of the security user.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status_id", nullable = false)
    private SecurityUserStatus status;

    /**
     * The security group of the security user.
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_group_id")
    private SecurityGroup securityGroup;

    /**
     * For getting the emailAddress.
     *
     * @return - the emailAddress.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * For setting the emailAddress.
     *
     * @param emailAddress
     *         - the emailAddress to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * For getting the passwordHash.
     *
     * @return - the passwordHash.
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * For setting the passwordHash.
     *
     * @param passwordHash
     *         - the passwordHash to set.
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * For getting the status.
     *
     * @return - the status.
     */
    public SecurityUserStatus getStatus() {
        return status;
    }

    /**
     * For setting the status.
     *
     * @param status
     *         - the status to set.
     */
    public void setStatus(SecurityUserStatus status) {
        this.status = status;
    }

    /**
     * For getting the securityGroup.
     *
     * @return - the securityGroup.
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
}
