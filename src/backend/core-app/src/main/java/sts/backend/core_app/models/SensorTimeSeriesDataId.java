package sts.backend.core_app.models;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class SensorTimeSeriesDataId implements Serializable {

    private LocalDateTime timestamp;
    private Long sensorId;
    private String metric;

    // standard constructors / setters / getters / toString
    public SensorTimeSeriesDataId() {}

    public SensorTimeSeriesDataId(Instant timestamp, Integer sensorId, String metric) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.metric = metric;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorTimeSeriesDataId that = (SensorTimeSeriesDataId) o;
        return Objects.equals(timestamp, that.timestamp) &&
               Objects.equals(sensorId, that.sensorId) &&
               Objects.equals(metric, that.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, sensorId, metric);
    }

}
