package sts.backend.core_app.dto.session;

public class SessionLastMetricValues {
    private Double heartRate;
    private Double bodyTemperature;
    private Double respiratoryRate;

    public SessionLastMetricValues(Double heartRate, Double bodyTemperature, Double respiratoryRate) {
        this.heartRate = heartRate;
        this.bodyTemperature = bodyTemperature;
        this.respiratoryRate = respiratoryRate;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public Double getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(Double bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Double getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Double respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }
}
