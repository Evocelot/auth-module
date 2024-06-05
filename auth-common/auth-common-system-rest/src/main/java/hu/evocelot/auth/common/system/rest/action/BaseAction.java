package hu.evocelot.auth.common.system.rest.action;

import jakarta.inject.Inject;

import hu.icellmobilsoft.coffee.cdi.logger.AppLogger;
import hu.icellmobilsoft.coffee.cdi.logger.ThisLogger;
import hu.icellmobilsoft.coffee.dto.common.commonservice.ContextType;
import hu.icellmobilsoft.coffee.rest.action.AbstractBaseAction;
import hu.icellmobilsoft.coffee.tool.utils.date.DateUtil;
import hu.icellmobilsoft.coffee.tool.utils.string.RandomUtil;

/**
 * Base class for all other business logic action class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class BaseAction extends AbstractBaseAction {

    @Inject
    @ThisLogger
    private AppLogger log;

    /**
     * Create a new Context for responses
     *
     * @return default filled context
     */
    public ContextType createContext() {
        ContextType context = new ContextType();
        context.setRequestId(RandomUtil.generateId());
        context.setTimestamp(DateUtil.nowUTCTruncatedToMillis());
        return context;
    }
}
