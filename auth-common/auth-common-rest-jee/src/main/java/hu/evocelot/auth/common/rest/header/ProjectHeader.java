package hu.evocelot.auth.common.rest.header;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import hu.icellmobilsoft.coffee.se.logging.Logger;
import hu.evocelot.auth.dto.constant.IHttpHeaderConstants;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MultivaluedMap;
import org.apache.commons.lang3.StringUtils;

/**
 * Project header class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class ProjectHeader implements IHttpHeaderConstants {

    /**
     * Http port separator.
     */
    private static final String HOST_PORT_SEPARATOR = ":";
    /**
     * Empty value.
     */
    public static final String EMPTY_VALUE = "empty";
    /**
     * Http header "For" key.
     */
    private static final String FORWARDED_FOR_TAG = "for=";
    /**
     * Http port max length for validation.
     */
    private static final int HOST_PORT_MAX_LENGTH = 15;

    /**
     * Session token value.
     */
    private String sessionToken;
    /**
     * Refresh token value.
     */
    private String refreshToken;
    /**
     * Host value.
     */
    private String host;
    /**
     * Source value.
     */
    private String source;
    /**
     * Language value.
     */
    private String language;
    /**
     * Forwarder value.
     */
    private String forwarded;
    /**
     * Forwarded for host value.
     */
    private String forwardedForHost;
    /**
     * Forwarded for port value.
     */
    private String forwardedForPort;

    /**
     * Read http header into bean fields.
     *
     * @param headers
     *         - the http header.
     * @return - with the header bean with filled fields.
     */
    public static ProjectHeader readHeaders(HttpHeaders headers) {
        if (headers == null) {
            return null;
        }
        return readHeaders(headers.getRequestHeaders());
    }

    /**
     * Read http header into bean fields.
     *
     * @param headerMap
     *         - the http header values map
     * @return - with the header bean with filled fields
     */
    public static ProjectHeader readHeaders(MultivaluedMap<String, String> headerMap) {
        ProjectHeader projectHeader = new ProjectHeader();
        if (headerMap != null) {

            String host = getHeaderValue(headerMap, HEADER_HOST, false);
            if (StringUtils.isBlank(host)) {
                host = getHeaderValue(headerMap, HEADER_XHOST, false);
            }
            projectHeader.setHost(host);

            projectHeader.setLanguage(getHeaderValue(headerMap, HEADER_LANGUAGE, false));
            projectHeader.setSessionToken(getHeaderValue(headerMap, HEADER_SESSION_TOKEN, true));
            projectHeader.setRefreshToken(getHeaderValue(headerMap, HEADER_REFRESH_TOKEN, false));
            projectHeader.setSource(getHeaderValue(headerMap, HEADER_SOURCE, false));
            projectHeader.setForwarded(getHeaderValue(headerMap, HEADER_FORWARDED, false));
        }
        return projectHeader;
    }

    private void handleForwardedHeader(String headerValue) {
        String host = null;
        String port = null;
        String hostPort = headerValue;
        if (StringUtils.isNotBlank(headerValue) && StringUtils.contains(headerValue, FORWARDED_FOR_TAG)) {
            String[] forwarded = StringUtils.split(headerValue, ";");
            Optional<String> o = Arrays.stream(forwarded).filter(s -> StringUtils.startsWithIgnoreCase(StringUtils.trim(s), FORWARDED_FOR_TAG))
                    .findFirst();
            if (o.isPresent()) {
                hostPort = StringUtils.substringAfter(o.get(), FORWARDED_FOR_TAG);
            }
        }
        if (StringUtils.contains(hostPort, HOST_PORT_SEPARATOR)) {
            host = StringUtils.trim(StringUtils.substringBefore(hostPort, HOST_PORT_SEPARATOR));
            port = StringUtils.trim(StringUtils.substringAfter(hostPort, HOST_PORT_SEPARATOR));
        } else {
            host = StringUtils.trim(hostPort);
        }
        setForwardedForHost(StringUtils.defaultString(StringUtils.left(host, HOST_PORT_MAX_LENGTH), EMPTY_VALUE));
        setForwardedForPort(StringUtils.defaultString(StringUtils.left(port, HOST_PORT_MAX_LENGTH), EMPTY_VALUE));
    }

    /**
     * Getting one value from header.
     *
     * @param headers
     *         - the http header.
     * @param key
     *         - the key of searching value.
     * @param required
     *         - is http header value mandatory? If true then print it to log.
     * @return - with value from http header.
     */
    public static String getHeaderValue(HttpHeaders headers, String key, boolean required) {
        if (headers == null || key == null) {
            return null;
        }
        return getHeaderValue(headers.getRequestHeaders(), key, required);
    }

    /**
     * Getting one value from header.
     *
     * @param headerMap
     *         - the http header values map.
     * @param key
     *         - the key of searching value.
     * @param required
     *         - is http header value mandatory? If true then print it to log
     * @return - with value from http header.
     */
    public static String getHeaderValue(MultivaluedMap<String, String> headerMap, String key, boolean required) {
        Logger log = Logger.getLogger(ProjectHeader.class);
        try {
            if (headerMap == null) {
                return null;
            }

            List<String> values = headerMap.get(key);
            if (values == null || values.isEmpty()) {
                String msg = "Request header doesn't contain (" + key + ") key";
                if (required) {
                    log.warn(msg);
                } else {
                    log.debug(msg);
                }
                return null;
            } else {
                return values.get(0);
            }
        } catch (Exception e) {
            log.warn("Error in getHeaderValue(" + key + ")", e);
            return null;
        }
    }

    /**
     * Get the session token.
     *
     * @return - with the session token.
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * Set the session token.
     *
     * @param sessionToken
     *         - the session token
     */
    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    /**
     * Get the refresh token.
     *
     * @return - with the refresh token.
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Set the refresh token.
     *
     * @param refreshToken
     *         - the refresh token.
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Get host.
     *
     * @return - with the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Set host.
     *
     * @param host
     *         - the host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Get source.
     *
     * @return - with the source.
     */
    public String getSource() {
        return source;
    }

    /**
     * Set source.
     *
     * @param source
     *         - the source.
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Get language.
     *
     * @return - with the language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set language.
     *
     * @param language
     *         - the language.
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get forwarded header.
     *
     * @return - with the forwarded header value.
     */
    public String getForwarded() {
        return forwarded;
    }

    /**
     * Set forwarded header value.
     *
     * @param forwarded
     *         - the forwarded value.
     */
    public void setForwarded(String forwarded) {
        this.forwarded = forwarded;
    }

    /**
     * Get forwarded header host value.
     *
     * @return - with the forwarded host value.
     */
    public String getForwardedForHost() {
        if (forwardedForHost == null && forwarded != null) {
            handleForwardedHeader(forwarded);
        }
        return forwardedForHost;
    }

    /**
     * Set forwarded for host value.
     *
     * @param forwardedForHost
     *         - the host value.
     */
    public void setForwardedForHost(String forwardedForHost) {
        this.forwardedForHost = forwardedForHost;
    }

    /**
     * Get forwarded header port value.
     *
     * @return - with the forwarded port value.
     */
    public String getForwardedForPort() {
        if (forwardedForPort == null && forwarded != null) {
            handleForwardedHeader(forwarded);
        }
        return forwardedForPort;
    }

    /**
     * Set forwarded for port value.
     *
     * @param forwardedForPort
     *         - the port value.
     */
    public void setForwardedForPort(String forwardedForPort) {
        this.forwardedForPort = forwardedForPort;
    }
}
