package hu.evocelot.auth.common.system.rest.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;

import hu.icellmobilsoft.coffee.cdi.logger.AppLogger;
import hu.icellmobilsoft.coffee.cdi.logger.ThisLogger;
import hu.icellmobilsoft.coffee.se.api.exception.BaseException;
import hu.evocelot.auth.common.rest.ISystemRest;

/**
 * Default system REST operations.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class SystemRest extends BaseRestService implements ISystemRest {

    @Inject
    @ThisLogger
    private AppLogger log;

    @Override
    public String versionInfo(HttpServletRequest servletRequest) throws BaseException {
        try {
            InputStream inputStream = servletRequest.getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF");
            StringBuilder version = new StringBuilder();
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br.readLine()) != null) {
                    version.append(line);
                    version.append("\n");
                }
            }
            return version.toString();
        } catch (Exception e) {
            throw baseExceptionWithLogging(e);
        }
    }
}
