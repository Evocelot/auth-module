package hu.evocelot.auth.dto.constant;

import hu.icellmobilsoft.coffee.dto.common.LogConstants;

/**
 * Basic REST HTTP header constants.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public interface IHttpHeaderConstants {

    /**
     * Session identifier over service. Value is main part of logging.
     */
    String HEADER_SID = LogConstants.LOG_SESSION_ID;

    /**
     * Authentication session token.
     */
    String HEADER_SESSION_TOKEN = "accessToken";

    /**
     * Authentication refresh token.
     */
    String HEADER_REFRESH_TOKEN = "refreshToken";

    /**
     * Authentication username.
     */
    String HEADER_USERNAME = "username";

    /**
     * Source host 1.
     */
    String HEADER_HOST = "HOST";

    /**
     * Source host 2.
     */
    String HEADER_XHOST = "X-HOST";

    /**
     * Request source: WEB, MOBIL, SERVICE, etc.
     */
    String HEADER_SOURCE = "X-SOURCE";

    /**
     * Application language.
     */
    String HEADER_LANGUAGE = "X-LANGUAGE";

    /**
     * Forwarded header.
     */
    String HEADER_FORWARDED = "FORWARDED";
}
