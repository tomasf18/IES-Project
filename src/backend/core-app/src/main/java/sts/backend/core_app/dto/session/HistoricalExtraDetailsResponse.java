package sts.backend.core_app.dto.session;

import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;

public class HistoricalExtraDetailsResponse extends HistoricalInfoResponse {
    private Double averageHeartRate;
    private Double averageBodyTemperature;
    private Double averageRespiratoryRate;
    

    public HistoricalExtraDetailsResponse(String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, Double averageHeartRate, Double averageBodyTemperature, Double averageRespiratoryRate) {
        super(sessionName, date, time, participants, historicalDataPlayers);
        this.averageHeartRate = averageHeartRate;
        this.averageBodyTemperature = averageBodyTemperature;
        this.averageRespiratoryRate = averageRespiratoryRate;
    }

    public Double getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(Double averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public Double getAverageBodyTemperature() {
        return averageBodyTemperature;
    }

    public void setAverageBodyTemperature(Double averageBodyTemperature) {
        this.averageBodyTemperature = averageBodyTemperature;
    }

    public Double getAverageRespiratoryRate() {
        return averageRespiratoryRate;
    }

    public void setAverageRespiratoryRate(Double averageRespiratoryRate) {
        this.averageRespiratoryRate = averageRespiratoryRate;
    }
}
