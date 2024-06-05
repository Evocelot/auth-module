package hu.evocelot.auth.common.system.rest.metrics.provider;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;

import org.apache.commons.lang3.StringUtils;

import hu.evocelot.auth.api.common.path.SamplePath;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * JAXRS provider for handling request/response metrics.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Provider
public class RequestResponseMetricsProvider implements ContainerRequestFilter, WriterInterceptor {

    @Inject
    private MetricsContainer metricsContainer;

    @Inject
    private MeterRegistry meterRegistry;

    @Context
    private HttpServletResponse httpServletResponse;

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String url = uriInfo.getAbsolutePath().toASCIIString();
        boolean standardHttp = StringUtils.containsAny(requestContext.getMethod(), HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT,
                HttpMethod.DELETE);
        if (standardHttp && StringUtils.contains(url, SamplePath.PREFIX_REST)) {
            metricsContainer.setStartTime(LocalDateTime.now());
        }
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        String url = uriInfo.getAbsolutePath().toASCIIString();
        if (metricsContainer.getStartTime() != null && StringUtils.contains(url, SamplePath.PREFIX_REST)) {
            updateResponseTimer(url);
        }
        context.proceed();
    }

    /**
     * {@value MetricsConstants.Timer#SAMPLE} metric refresh
     *
     * @param url
     *         http request url
     */
    private void updateResponseTimer(String url) {
        Timer timer = Timer.builder("sample_http_response_time").description("Input HTTP sample request count and response times.").tag("url", url)
                .register(meterRegistry);
        timer.record(Duration.between(metricsContainer.getStartTime(), LocalDateTime.now()));
    }
}
