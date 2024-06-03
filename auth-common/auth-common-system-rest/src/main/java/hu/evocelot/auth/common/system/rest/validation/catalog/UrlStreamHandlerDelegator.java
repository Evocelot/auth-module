package hu.evocelot.auth.common.system.rest.validation.catalog;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of URLStreamHandlerFactory, delegates every call to its members.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
public class UrlStreamHandlerDelegator implements URLStreamHandlerFactory {

    private final List<URLStreamHandlerFactory> urlStreamHandlerFactories = new ArrayList<>();

    /**
     * Creates a new {@code URLStreamHandler} instance with the specified protocol.
     *
     * @param protocol
     *         - the protocol ("{@code ftp}", "{@code http}", "{@code nntp}", etc.).
     * @return - with a {@code URLStreamHandler} for the specific protocol, or {@code null} if this factory cannot create a handler for the specific
     *         protocol
     * @see URLStreamHandler
     */
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        for (URLStreamHandlerFactory urlStreamHandlerFactory : urlStreamHandlerFactories) {
            URLStreamHandler urlStreamHandler = urlStreamHandlerFactory.createURLStreamHandler(protocol);
            if (urlStreamHandler != null) {
                return urlStreamHandler;
            }
        }
        return null;
    }

    /**
     * Get own url factory list.
     *
     * @return - with the list of url factory.
     */
    public List<URLStreamHandlerFactory> getUrlStreamHandlerFactories() {
        return urlStreamHandlerFactories;
    }

    /**
     * Add new url factory to URL.
     *
     * @param urlStreamHandlerFactory
     *         - the added url factory.
     */
    public void addUrlStreamHandlerFactory(URLStreamHandlerFactory urlStreamHandlerFactory) {
        this.urlStreamHandlerFactories.add(urlStreamHandlerFactory);
    }
}
