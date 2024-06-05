package hu.evocelot.auth.common.system.rest.validation.json;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseRequestType;
import hu.icellmobilsoft.coffee.rest.validation.json.JsonMessageBodyReaderBase;

/**
 * MessageBodyReader for json requests.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
@Consumes({ MediaType.APPLICATION_JSON })
@Priority(Priorities.ENTITY_CODER)
@Dependent
public class JsonRequestMessageBodyReader extends JsonMessageBodyReaderBase<BaseRequestType> {
}
