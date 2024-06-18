package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class SecurityGroupRestInformation {

    /**
     * {@value }
     */
    public static final String TAG = "Security group management";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Security group management for handling the security group operations";

    /**
     * {@value}.
     */
    public static final String SECURITY_GROUP_ID_PARAM_SUMMARY = "The unique identifier of the security group.";

    /**
     * {@value}.
     */
    public static final String CREATE_SECURITY_GROUP_SUMMARY = "Create security group";

    /**
     * {@value}.
     */
    public static final String CREATE_SECURITY_GROUP_DESCRIPTION = "Endpoint for creating new security groups.";

    /**
     * {@value}.
     */
    public static final String UPDATE_SECURITY_GROUP_SUMMARY = "Update security group";

    /**
     * {@value}.
     */
    public static final String UPDATE_SECURITY_GROUP_DESCRIPTION = "Endpoint for modifying security groups.";

    /**
     * {@value}.
     */
    public static final String DELETE_SECURITY_GROUP_SUMMARY = "Delete security group";

    /**
     * {@value}.
     */
    public static final String DELETE_SECURITY_GROUP_DESCRIPTION = "Endpoint for deleting security groups.";

    /**
     * {@value}.
     */
    public static final String GET_SECURITY_GROUP_SUMMARY = "Get security group";

    /**
     * {@value}.
     */
    public static final String GET_SECURITY_GROUP_DESCRIPTION = "Endpoint for getting base details of the security group.";

    /**
     * {@value}.
     */
    public static final String ADD_PERMISSION_TO_SECURITY_GROUP_SUMMARY = "Add permission to the security group";

    /**
     * {@value}.
     */
    public static final String ADD_PERMISSION_TO_SECURITY_GROUP_DESCRIPTION = "Endpoint for adding permission to the security group.";

    /**
     * {@value}.
     */
    public static final String DELETE_PERMISSION_FROM_SECURITY_GROUP_SUMMARY = "Delete permission from the security group";

    /**
     * {@value}.
     */
    public static final String DELETE_PERMISSION_FROM_SECURITY_GROUP_DESCRIPTION = "Endpoint for deleting permission from the security group.";

    /**
     * {@value}.
     */
    public static final String QUERY_SECURITY_GROUP_SUMMARY = "Listing security groups";

    /**
     * {@value}.
     */
    public static final String QUERY_SECURITY_GROUP_DESCRIPTION = "Endpoint for listing security groups with filtering, sorting and paging option.";
}
