package hu.evocelot.auth.service.auth.interceptor;

/**
 * Possible permissions that can be used to protect individual functions. Use these permissions on methods annotated with 'secured' to prevent
 * unauthorized access.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public enum Permission {
    /**
     * Permission for creating users.
     */
    CREATE_USER,

    /**
     * Permission for getting user details.
     */
    GET_USER,

    /**
     * Permission for querying users.
     */
    QUERY_USER,

    /**
     * Permission for deleting users.
     */
    DELETE_USER,

    /**
     * Permission for updating security settings of a user.
     */
    UPDATE_SECURITY_USER,

    /**
     * Permission for updating security settings of the current user.
     */
    UPDATE_CURRENT_SECURITY_USER,

    /**
     * Permission for updating partners.
     */
    UPDATE_PARTNER,

    /**
     * Permission for updating the current partner.
     */
    UPDATE_CURRENT_PARTNER,

    /**
     * Permission for updating permissions.
     */
    UPDATE_PERMISSION,

    /**
     * Permission for getting permission details.
     */
    GET_PERMISSION,

    /**
     * Permission for querying permissions.
     */
    QUERY_PERMISSION,

    /**
     * Permission for creating security groups.
     */
    CREATE_SECURITY_GROUP,

    /**
     * Permission for updating security groups.
     */
    UPDATE_SECURITY_GROUP,

    /**
     * Permission for deleting security groups.
     */
    DELETE_SECURITY_GROUP,

    /**
     * Permission for getting security group details.
     */
    GET_SECURITY_GROUP,

    /**
     * Permission for querying security groups.
     */
    QUERY_SECURITY_GROUP,

    /**
     * Permission for adding a permission to a security group.
     */
    ADD_PERMISSION_TO_SECURITY_GROUP,

    /**
     * Permission for uploading profile picture.
     */
    UPLOAD_PROFILE_PICTURE,

    /**
     * Permission for downloading profile picture.
     */
    DOWNLOAD_PROFILE_PICTURE,

    /**
     * Permission for removing permission from the security group.
     */
    REMOVE_PERMISSION_FROM_SECURITY_GROUP,

    /**
     * Permission for changing the current user password.
     */
    CHANGE_CURRENT_PASSWORD,
}
