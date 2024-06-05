package hu.evocelot.ts.auth.common.dto;

import hu.icellmobilsoft.coffee.dto.common.commonservice.ContextType;
import hu.icellmobilsoft.coffee.tool.utils.date.DateUtil;
import hu.icellmobilsoft.coffee.tool.utils.string.RandomUtil;

/**
 * DTO helper class.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class DtoHelper {

    /**
     * Create basic ContextType.
     *
     * @return - with the filled context.
     */
    public static ContextType createContext() {
        ContextType context = new ContextType();
        context.setRequestId(RandomUtil.generateId());
        context.setTimestamp(DateUtil.nowUTCTruncatedToMillis());
        return context;
    }
}
