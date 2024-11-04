package sts.backend.core_app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
@IdClass(SensorTimeSeriesDataId.class) // Specify the composite key class (ID is a composite key, so we need to create a class for it)
public class SensorTimeSeriesData {

    @Id
    private LocalDateTime timestamp;

    @Id
    private Long playerId;

    @Id
    private String metric;

    @Column(nullable = false)
    private Double value;

    // standard constructors / setters / getters / toString
    public SensorTimeSeriesData() {}

    public SensorTimeSeriesData(LocalDateTime timestamp, Long playerId, String metric, Double value) {
        this.timestamp = timestamp;
        this.playerId = playerId;
        this.metric = metric;
        this.value = value;
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
                ", playerId=" + playerId +
                ", metric='" + metric + '\'' +
                ", value=" + value +
                '}';
    }
}
