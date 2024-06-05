package hu.evocelot.auth.common.system.rest.validation.catalog;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandlerFactory;
import java.text.MessageFormat;

import hu.icellmobilsoft.coffee.rest.validation.catalog.MavenURLStreamHandlerProvider;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

/**
 * Common application starter. Starting on cdi {@code ApplicationScoped} initializing.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@ApplicationScoped
public class Starter {

    private static final Logger LOGGER = Logger.getLogger(Starter.class);

    /**
     * Must be set maven url resolver into URL factory.
     *
     * @param init
     *         - the cdi init object.
     */
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        String pkgs = System.getProperty("java.protocol.handler.pkgs", "");
        LOGGER.debug(MessageFormat.format("\n\nStarter\n\npkgs: [{0}]\n\n", pkgs));

        UrlStreamHandlerDelegator delegator = new UrlStreamHandlerDelegator();
        delegator.addUrlStreamHandlerFactory(new MavenURLStreamHandlerProvider());
        try {
            // Try doing it the normal way
            URL.setURLStreamHandlerFactory(delegator);
        } catch (final Error e) {
            // Force it via reflection (eleg durva hacking, de minden mas megoldas nem mukodik)
            try {
                final Field factoryField = URL.class.getDeclaredField("factory");
                factoryField.setAccessible(true);
                // A már beállított (default) factory-t beállítjuk a delegatornak, hogy az is tudjon futni
                URLStreamHandlerFactory factory = (URLStreamHandlerFactory) factoryField.get(null);
                delegator.addUrlStreamHandlerFactory(factory);
                factoryField.set(null, delegator);
            } catch (NoSuchFieldException | IllegalAccessException e1) {
                throw new Error("Could not access factory field on URL class: {}", e);
            }
        }
    }
}
