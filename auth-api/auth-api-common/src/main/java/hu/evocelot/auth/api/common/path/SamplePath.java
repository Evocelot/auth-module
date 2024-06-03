package hu.evocelot.auth.api.common.path;

import hu.icellmobilsoft.coffee.dto.url.BaseServicePath;

/**
 * Proxy service REST url path storage.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class SamplePath extends BaseServicePath {

    /**
     * Rest path prefix for public UI (Frontend, Mobile...) category URL.
     */
    public static final String PREFIX_REST = "/rest";

    /**
     * The {@value } path.
     */
    public static final String SAMPLE = "/sample";

    /**
     * The root {@value } path for the sample service.
     */
    public static final String REST_SAMPLE_SERVICE = PREFIX_REST + SAMPLE;
}
