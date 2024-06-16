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
    public static final String SECURITY_USER_SERVICE = "/security-user";

    /**
     * The {@value } path.
     */
    public static final String PARTNER_SERVICE = "/partner";

    /**
     * The {@value } path.
     */
    public static final String USER_SERVICE = "/user";

    /**
     * The {@value } path.
     */
    public static final String SECURITY_GROUP_SERVICE = "/security-group";

    /**
     * The {@value } path.
     */
    public static final String PERMISSION_SERVICE = "/permission";

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
}
