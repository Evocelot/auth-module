package hu.evocelot.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import hu.icellmobilsoft.coffee.model.base.javatime.AbstractIdentifiedAuditEntity;

/**
 * The partner entity.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@Entity
@Table(name = "partner")
public class Partner extends AbstractIdentifiedAuditEntity {

    /**
     * The first name of the partner.
     */
    @Column(name = "first_name", length = 150, nullable = false)
    private String firstName;

    /**
     * The last name of the partner.
     */
    @Column(name = "last_name", length = 150, nullable = false)
    private String lastName;

    /**
     * The phone number of the partner.
     */
    @Column(name = "phone_number", length = 20, nullable = true)
    private String phoneNumber;

    /**
     * The id of the profile picture for the partner.
     */
    @Column(name = "profile_picture_id", length = 30, nullable = true)
    private String profilePictureId;

    /**
     * The security user of the partner.
     */
    @NotNull
    @JoinColumn(name = "security_user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SecurityUser securityUser;

    /**
     * For getting the firstName.
     * @return - with the firstName.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * For setting the firstName.
     * @param firstName - the firstName to set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * For getting the lastName.
     * @return - with the lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * For setting the lastName.
     * @param lastName - the lastName to set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * For getting the phoneNumber.
     * @return - with the phoneNumber.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * For setting the phoneNumber.
     * @param phoneNumber - the phoneNumber to set.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * For getting the securityUser.
     * @return - with the securityUser.
     */
    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    /**
     * For setting the securityUser.
     * @param securityUser - the securityUser to set.
     */
    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }

    /**
     * For getting the profilePictureId.
     * @return - with the profilePictureId.
     */
    public String getProfilePictureId() {
        return profilePictureId;
    }

    /**
     * For setting the profilePictureId.
     * @param profilePictureId - the profilePictureId to set.
     */
    public void setProfilePictureId(String profilePictureId) {
        this.profilePictureId = profilePictureId;
    }
}
