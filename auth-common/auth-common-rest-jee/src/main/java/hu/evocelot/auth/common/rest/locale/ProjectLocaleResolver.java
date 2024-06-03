package hu.evocelot.auth.common.rest.locale;

import java.util.Locale;

import hu.icellmobilsoft.coffee.cdi.logger.AppLogger;
import hu.icellmobilsoft.coffee.cdi.logger.ThisLogger;
import hu.evocelot.auth.common.rest.header.ProjectHeader;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import org.apache.commons.lang3.StringUtils;
import org.apache.deltaspike.core.impl.message.DefaultLocaleResolver;

/**
 * Locale resolver by header {@value ProjectHeader#HEADER_LANGUAGE} value.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Dependent
@Alternative
@Priority(Interceptor.Priority.APPLICATION + 10)
public class ProjectLocaleResolver extends DefaultLocaleResolver {

    private static final long serialVersionUID = 1L;

    /**
     * The default language.
     */
    public static final String DEFAULT_LANGUAGE = "hu";

    /**
     * The project header.
     */
    @Inject
    private ProjectHeader header;

    /**
     * The app logger.
     */
    @Inject
    @ThisLogger
    private AppLogger log;

    @Override
    public Locale getLocale() {
        if (header != null) {
            log.debug("header language: [{0}]", header.getLanguage());
            String language = header.getLanguage();
            if (StringUtils.isNotBlank(language)) {
                return new Locale(language);
            }
        }
        return new Locale(DEFAULT_LANGUAGE);
    }
}
