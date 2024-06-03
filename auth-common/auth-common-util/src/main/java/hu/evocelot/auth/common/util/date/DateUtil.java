package hu.evocelot.auth.common.util.date;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * Date util.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class DateUtil extends hu.icellmobilsoft.coffee.tool.utils.date.DateUtil {

    /**
     * Converts date to UTC OffsetDateTime
     *
     * @param date
     *         date to convert
     * @return {@link OffsetDateTime} in UTC
     */
    public static OffsetDateTime toUTCOffsetDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

}
