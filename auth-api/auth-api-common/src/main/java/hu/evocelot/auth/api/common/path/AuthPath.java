package hu.evocelot.auth.api.common.path;

import jakarta.enterprise.context.ApplicationScoped;

import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;

/**
 * Path class for defining the service paths.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
@ApplicationScoped
public class AuthPath extends BaseServicePath {

    /**
     * The {@value } path.
     */
    public static final String BASE_PATH = "/auth-service";

    /**
     * The {@value } path.
     */
    public static final String SECURITY_USER_MANAGEMENT = BASE_PATH + "/security-user";

    /**
     * The {@value } path.
     */
    public static final String PARTNER_MANAGEMENT = BASE_PATH + "/partner";

    /**
     * The {@value } path.
     */
    public static final String USER_MANAGEMENT = BASE_PATH + "/user";

    /**
     * The {@value } path.
     */
    public static final String SECURITY_GROUP_MANAGEMENT = BASE_PATH + "/security-group";

    /**
     * The {@value } path.
     */
    public static final String PERMISSION_MANAGEMENT = BASE_PATH + "/permission";

    /**
     * The {@value } path.
     */
    public static final String AUTH_MANAGEMENT = BASE_PATH + "/auth";

    /**
     * The {@value } path.
     */
    public static final String TOKEN_VALUE_PARAM = "token-value";

    /**
     * The {@value } path.
     */
    public static final String CURRENT = "/current";

    /**
     * The {@value } path.
     */
    public static final String CHANGE_PASSWORD = "/change-password";

    /**
     * The {@value } path.
     */
    public static final String PROFILE_PICTURE = "/profile-picture";

    /**
     * The {@value } path.
     */
    public static final String ADD_PERMISSION = "/add-permission";

    /**
     * The {@value } path.
     */
    public static final String DELETE_PERMISSION = "/remove-permission";

    /**
     * The {@value } path.
     */
    public static final String LOGIN = "/login";

    /**
     * The {@value } path.
     */
    public static final String LOGOUT = "/logout";

    /**
     * The {@value } path.
     */
    public static final String REFRESH_ACCESS = "/get-new-access-token";

    /**
     * The {@value } path.
     */
    public static final String TOKEN_VALUE = "/{" + TOKEN_VALUE_PARAM + "}";
}
