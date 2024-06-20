package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class AuthRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "Auth management";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Auth management for handling the authentication operations";

    /**
     * {@value }
     */
    public static final String LOGIN_SUMMARY = "Login";

    /**
     * {@value }
     */
    public static final String LOGIN_DESCRIPTION = "Endpoint for logging in. This endpoint provides the user with access and refresh tokens.";

    /**
     * {@value }
     */
    public static final String LOGOUT_SUMMARY = "Logout";

    /**
     * {@value }
     */
    public static final String LOGOUT_DESCRIPTION = "Endpoint for logging out. This endpoint invalidates the current access token but retains the refresh token.";

    /**
     * {@value }
     */
    public static final String GET_NEW_ACCESS_TOKEN_SUMMARY = "Get new access token";

    /**
     * {@value }
     */
    public static final String GET_NEW_ACCESS_TOKEN_DESCRIPTION = "Endpoint for obtaining a new access token. This endpoint invalidates the original access token.";

    /**
     * {@value }
     */
    public static final String REFRESH_TOKEN_PARAM_DESCRIPTION = "The value of the refresh token.";
}
