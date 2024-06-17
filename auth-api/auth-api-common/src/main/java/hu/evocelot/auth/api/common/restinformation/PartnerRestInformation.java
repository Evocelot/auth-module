package hu.evocelot.auth.api.common.restinformation;

/**
 * Class for defining the rest information.
 *
 * @author mark.danisovszky
 * @since 0.10.0
 */
public class PartnerRestInformation {
    /**
     * {@value }
     */
    public static final String TAG = "Partner service";

    /**
     * {@value }
     */
    public static final String DESCRIPTION = "Partner service for handling the partner operations";

    /**
     * {@value }
     */
    public static final String PARTNER_ID_PARAM_SUMMARY = "The unique identifier for the partner.";

    /**
     * {@value }
     */
    public static final String UPDATE_PARTNER_SUMMARY = "Update partner";

    /**
     * {@value }
     */
    public static final String UPDATE_PARTNER_DESCRIPTION = "Endpoint for updating the partner.";

    /**
     * {@value }
     */
    public static final String UPDATE_CURRENT_PARTNER_SUMMARY = "Update current partner";

    /**
     * {@value }
     */
    public static final String UPDATE_CURRENT_PARTNER_DESCRIPTION = "Endpoint for updating the current (logged) partner.";

    /**
     * {@value }
     */
    public static final String UPLOAD_PROFILE_PICTURE_SUMMARY = "Upload profile picture";

    /**
     * {@value }
     */
    public static final String UPLOAD_PROFILE_PICTURE_DESCRIPTION =
            "Endpoint for uploading profile pictures. " + "To use this endpoint, you have to use the file-service too. "
                    + "When uploading new profile picture to a partner, the original profile picture will be deleted.";

    /**
     * {@value }
     */
    public static final String DOWNLOAD_PROFILE_PICTURE_SUMMARY = "Download profile picture";

    /**
     * {@value }
     */
    public static final String DOWNLOAD_PROFILE_PICTURE_DESCRIPTION = "Endpoint for downloading profile pictures. To use this endpoint, you have to use the file-service too. ";
}
