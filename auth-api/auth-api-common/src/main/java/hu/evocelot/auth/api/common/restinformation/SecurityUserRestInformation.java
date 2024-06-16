package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class SecurityUserRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "Security user service";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Security user service for handling the security user operations";

    /**
     * {@value }
     */
    public static final String SECURITY_USER_ID_PARAM_SUMMARY = "The unique identifier for the security user.";

    /**
     * {@value }
     */
    public static final String GET_SECURITY_USER_SUMMARY = "Get security user";

    /**
     * {@value }
     */
    public static final String GET_SECURITY_USER_DESCRIPTION = "Endpoint for getting the security user.";

    /**
     * {@value }
     */
    public static final String UPDATE_SECURITY_USER_SUMMARY = "Update security user";

    /**
     * {@value }
     */
    public static final String UPDATE_SECURITY_USER_DESCRIPTION = "Endpoint for updating the security user.";

    /**
     * {@value }
     */
    public static final String UPDATE_CURRENT_SECURITY_USER_SUMMARY = "Update current security user";

    /**
     * {@value }
     */
    public static final String UPDATE_CURRENT_SECURITY_USER_DESCRIPTION = "Endpoint for updating the current (logged) security user.";

    /**
     * {@value }
     */
    public static final String CHANGE_CURRENT_PASSWORD_SUMMARY = "Change current password";

    /**
     * {@value }
     */
    public static final String CHANGE_CURRENT_PASSWORD_DESCRIPTION = "Endpoint for changing the current (logged) security users password.";
}
