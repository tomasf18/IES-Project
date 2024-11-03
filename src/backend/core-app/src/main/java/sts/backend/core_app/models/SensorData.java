package sts.backend.core_app.models;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "sensor_data")
@IdClass(SensorDataId.class) // Specify the composite key class (ID is a composite key, so we need to create a class for it)
public class SensorData {

    @Id
    private Instant timestamp;

    @Id
    private Integer sensorId;

    @Id
    private String metric;

    @Column(nullable = false)
    private Double value;

    // Constructors, getters, and setters

    public SensorData() {}

    public SensorData(Instant timestamp, Integer sensorId, String metric, Double value) {
        this.timestamp = timestamp;
        this.sensorId = sensorId;
        this.metric = metric;
        this.value = value;
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "timestamp=" + timestamp +
                ", sensorId=" + sensorId +
                ", metric='" + metric + '\'' +
                ", value=" + value +
                '}';
    }
}
