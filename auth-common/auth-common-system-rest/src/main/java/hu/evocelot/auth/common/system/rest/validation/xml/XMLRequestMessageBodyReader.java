package hu.evocelot.auth.common.system.rest.validation.xml;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.Provider;

import hu.icellmobilsoft.coffee.dto.common.commonservice.BaseRequestType;
import hu.icellmobilsoft.coffee.rest.validation.xml.XmlMessageBodyReaderBase;

/**
 * MessageBodyReader for xml requests.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
@Consumes({ MediaType.APPLICATION_XML, MediaType.TEXT_XML })
@Priority(Priorities.ENTITY_CODER)
@Dependent
public class XMLRequestMessageBodyReader extends XmlMessageBodyReaderBase<BaseRequestType> {

}
