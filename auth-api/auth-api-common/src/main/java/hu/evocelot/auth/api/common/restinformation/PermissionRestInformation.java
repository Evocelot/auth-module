package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class PermissionRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "Permission management";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Permission management for handling the permission operations";

    /**
     * {@value }
     */
    public static final String PERMISSION_ID_PARAM_SUMMARY = "The unique identifier of the permission.";

    /**
     * {@value }
     */
    public static final String GET_PERMISSION_SUMMARY = "Get permission";

    /**
     * {@value }
     */
    public static final String GET_PERMISSION_DESCRIPTION = "Endpoint for getting the base details of the permission.";

    /**
     * {@value }
     */
    public static final String UPDATE_PERMISSION_SUMMARY = "Update permission";

    /**
     * {@value }
     */
    public static final String UPDATE_PERMISSION_DESCRIPTION = "Endpoint for updating the base details of the permission.";

    /**
     * {@value}.
     */
    public static final String QUERY_PERMISSION_SUMMARY = "Listing permissions";

    /**
     * {@value}.
     */
    public static final String QUERY_PERMISSION_DESCRIPTION = "Endpoint for listing permissions with filtering, sorting and paging option.";
}
