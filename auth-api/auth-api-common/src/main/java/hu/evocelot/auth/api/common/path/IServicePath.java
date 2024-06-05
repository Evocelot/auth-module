package hu.evocelot.auth.api.common.path;

/**
 * Base http path constants.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public interface IServicePath {

    /**
     * empty String.
     */
    String EMPTY = "";

    /**
     * '?' String for http rest parameter concatenation.
     */
    String SIGN_QUESTION = "?";

    /**
     * '=' String for http rest parameter value.
     */
    String SIGN_EQUALS = "=";

    /**
     * &amp; String for http rest parameter concatenation.
     */
    String SIGN_AND = "&";

    /**
     * Rest path prefix for public UI (Frontend, Mobile...) category URL.
     */
    String PREFIX_REST = "/rest";

    /**
     * Rest path prefix for component inner communication (another inner service) category URL.
     */
    String PREFIX_SYSTEM = "/system";

    /**
     * Rest path prefix for public UI and service withput authentication (Frontend, Mobile, API) category URL.
     */
    String PREFIX_PUBLIC = "/public";

    /**
     * Rest path prefix for external partner (ticketing partner, toll system...) category URL.
     */
    String PREFIX_EXTERNAL = "/external";

    /**
     * Rest path prefix for internal partner service (document service, paying service) category URL.
     */
    String PREFIX_INTERNAL = "/internal";

    /**
     * Rest path prefix for internal test service (data generators, flow accelerator) category URL.
     */
    String PREFIX_TEST = "/test";

    /**
     * Rest path prefix for maintenance service (for administrators) category URL.
     */
    String PREFIX_MAINTENANCE = "/maintenance";

    /**
     * Common constant for identifier.
     */
    String PARAM_ID = "id";

    /**
     * Common constant for identifier applicated in rest path.
     */
    String ID = "/{" + PARAM_ID + "}";

    /**
     * Common constant for user identifier.
     */
    String PARAM_USER_ID = "userId";

    /**
     * Common constant for user identifier applicated in rest path.
     */
    String USER_ID = "/{" + PARAM_USER_ID + "}";

    /**
     * Common constant for date.
     */
    String PARAM_DATE = "date";

    /**
     * Common constant for date applicated in rest path.
     */
    String DATE = "/{" + PARAM_DATE + "}";

    /**
     * Common constant for from-date applicated in rest path.
     */
    String PARAM_FROM_DATE_TIME = "fromDateTime";

    /**
     * Common constant for to-date applicated in rest path.
     */
    String PARAM_TO_DATE_TIME = "toDateTime";

    /**
     * Common constant for size limit applicated in rest path.
     */
    String PARAM_LIMIT = "limit";

    /**
     * Rest path common constant for business list content response in request rest path.
     */
    String LIST = "/list";

    /**
     * Rest path common constant for business info content response in request rest path.
     */
    String INFO = "/info";

    /**
     * Common constant for business paginated query list content response in request rest path.
     */
    String QUERY = "/query";

    /**
     * Rest path common constant for META-INF/MANIFEST.MF file content in response.
     */
    String VERSION_INFO = "/versionInfo";

    /**
     * Rest path common constant for evict all cache data in service.
     */
    String EVICT = "/evict";

    /**
     * Rest path common constant for fail over.
     */
    String FAILOVER = "/failover";

    /**
     * Rest path common constant for reprocess.
     */
    String REPROCESS = "/reprocess";

    /**
     * Rest path common constant for checking.
     */
    String CHECK = "/check";

    /**
     * Rest path common constant for cleanup.
     */
    String CLEANUP = "/cleanup";
}
