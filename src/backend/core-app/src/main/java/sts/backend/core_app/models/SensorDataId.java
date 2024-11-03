package sts.backend.core_app.models;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class SensorDataId implements Serializable {

    private Instant timestamp;
    private Integer sensorId;
    private String metric;

    public SensorDataId() {}

    public SensorDataId(Instant timestamp, Integer sensorId, String metric) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.metric = metric;
    }

    // Getters, setters, equals, and hashCode
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
        SensorDataId that = (SensorDataId) o;
        return Objects.equals(timestamp, that.timestamp) &&
               Objects.equals(sensorId, that.sensorId) &&
               Objects.equals(metric, that.metric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, sensorId, metric);
    }

}
