package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class UserRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "User management";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "User management for handling the user operations";

    /**
     * {@value }
     */
    public static final String CREATE_USER_SUMMARY = "Create user";

    /**
     * {@value }
     */
    public static final String CREATE_USER_DESCRIPTION = "Endpoint for creating user (security user + partner).";

    /**
     * {@value }
     */
    public static final String GET_USER_SUMMARY = "Get user";

    /**
     * {@value }
     */
    public static final String GET_USER_DESCRIPTION = "Endpoint for getting user (security user + partner).";

    /**
     * {@value }
     */
    public static final String DELETE_USER_SUMMARY = "Delete user";

    /**
     * {@value }
     */
    public static final String DELETE_USER_DESCRIPTION = "Endpoint for deleting user (security user + partner).";

    /**
     * {@value}.
     */
    public static final String QUERY_USER_SUMMARY = "Listing users";

    /**
     * {@value}.
     */
    public static final String QUERY_USER_DESCRIPTION = "Endpoint for listing users with filtering, sorting and paging option.";

    /**
     * {@value}.
     */
    public static final String GET_CURRENT_USER_SUMMARY = "Get current (logged) user";

    /**
     * {@value}.
     */
    public static final String GET_CURRENT_USER_DESCRIPTION = "Endpoint for getting the current (logged) user based on the access token.";
}
