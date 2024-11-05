package sts.backend.core_app.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class SensorTimeSeriesDataId implements Serializable {

    private LocalDateTime timestamp;
    private Long playerId;
    private String metric;

    // standard constructors / setters / getters / toString
    public SensorTimeSeriesDataId() {}

    public SensorTimeSeriesDataId(LocalDateTime timestamp, Long playerId, String metric) {
        this.timestamp = timestamp;
        this.playerId = playerId;
        this.metric = metric;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setSensorId(Long playerId) {
        this.playerId = playerId;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

}
