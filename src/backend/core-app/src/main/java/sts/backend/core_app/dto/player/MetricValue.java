package sts.backend.core_app.dto.player;

public class MetricValue {
    private Long playerId;
    private String metricName;
    private Double value;

    public MetricValue() {
    }

    public MetricValue(Long playerId, String metricName, Double value) {
        this.playerId = playerId;
        this.metricName = metricName;
        this.value = value;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public String getMetricName() {
        return metricName;
    }

    public Double getValue() {
        return value;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
