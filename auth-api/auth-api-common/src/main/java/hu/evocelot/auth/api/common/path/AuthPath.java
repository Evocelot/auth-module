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
    public static final String SECURITY_USER_SERVICE = BASE_PATH + "/security-user";

    /**
     * The {@value } path.
     */
    public static final String PARTNER_SERVICE = BASE_PATH + "/partner";

    /**
     * The {@value } path.
     */
    public static final String USER_SERVICE = BASE_PATH + "/user";

    /**
     * The {@value } path.
     */
    public static final String SECURITY_GROUP_SERVICE = BASE_PATH + "/security-group";

    /**
     * The {@value } path.
     */
    public static final String PERMISSION_SERVICE = BASE_PATH + "/permission";

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
}
