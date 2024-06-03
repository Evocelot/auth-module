package hu.evocelot.auth.common.system.rest.metrics.provider;

import java.time.LocalDateTime;

import jakarta.enterprise.inject.Model;

/**
 * Microprofile-metrics RequestScope container.
 *
 * @author mark.danisovszky
 * @since 0.1.0
 */
@Model
public class MetricsContainer {

    private LocalDateTime startTime;

    /**
     * Get start time for metrics.
     *
     * @return - with the start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Set start time for metrics.
     *
     * @param startTime
     *         - the start time.
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
