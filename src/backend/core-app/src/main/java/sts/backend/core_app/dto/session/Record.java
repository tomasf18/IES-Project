package sts.backend.core_app.dto.session;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Record {
    @JsonProperty("sensor_id")
    private Long sensorId;

    @JsonProperty("heart_rate")
    private double heartRate;

    @JsonProperty("respiratory_rate")
    private double respiratoryRate;

    @JsonProperty("body_temperature")
    private double bodyTemperature;

    public Record() {
    }

    public Record(Long sensorId, Double heartRate, Double respiratoryRate, Double bodyTemperature) {
        this.sensorId = sensorId;
        this.heartRate = heartRate;
        this.respiratoryRate = respiratoryRate;
        this.bodyTemperature = bodyTemperature;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Double getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Double respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    @Override
    public String toString() {
        return  "sensor_id=" + sensorId +
                ", heart_rate=" + heartRate +
                ", respiratory_rate=" + respiratoryRate +
                ", body_temperature=" + bodyTemperature;
    }

    

}
