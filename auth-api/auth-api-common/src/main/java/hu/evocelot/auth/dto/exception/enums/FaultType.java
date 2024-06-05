package hu.evocelot.auth.dto.exception.enums;

import hu.icellmobilsoft.coffee.cdi.annotation.FaultTypeCode;

/**
 * Project specific faults.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@FaultTypeCode
public enum FaultType {
    /**
     * HTTP 400 Response.Status.BAD_REQUEST<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.BadRequestException
     */
    REST_BAD_REQUEST,

    /**
     * HTTP 401 Response.Status.UNAUTHORIZED<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.NotAuthorizedException
     */
    REST_UNAUTHORIZED,

    /**
     * HTTP 403 Response.Status.FORBIDDEN<br> Handling of TechnicalFault in localized language<br> Original source is javax.ws.rs.ForbiddenException
     */
    REST_FORBIDDEN,

    /**
     * HTTP 404 Response.Status.NOT_FOUND<br> Handling of TechnicalFault in localized language<br> Original source is javax.ws.rs.NotFoundException
     */
    REST_NOT_FOUND,

    /**
     * HTTP 405 Response.Status.METHOD_NOT_ALLOWED<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.NotAllowedException
     */
    REST_METHOD_NOT_ALLOWED,

    /**
     * HTTP 406 Response.Status.NOT_ACCEPTABLE<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.NotAcceptableException
     */
    REST_NOT_ACCEPTABLE,

    /**
     * HTTP 415 Response.Status.UNSUPPORTED_MEDIA_TYPE<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.NotSupportedException
     */
    REST_UNSUPPORTED_MEDIA_TYPE,

    /**
     * HTTP 500 Response.Status.INTERNAL_SERVER_ERROR<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.InternalServerErrorException
     */
    REST_INTERNAL_SERVER_ERROR,

    /**
     * HTTP 503 Response.Status.SERVICE_UNAVAILABLE<br> Handling of TechnicalFault in localized language<br> Original source is
     * javax.ws.rs.ServiceUnavailableException
     */
    REST_SERVICE_UNAVAILABLE,

    /**
     * HTTP 418, when the sample entity is not found.
     */
    SAMPLE_NOT_FOUND,
}
