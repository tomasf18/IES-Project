package sts.backend.core_app.dto.session;

import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;

public class HistoricalExtraDetailsResponse extends HistoricalInfoResponse {
    private Double averageHeartRate;
    private Double averageBodyTemperature;
    private Double averageRespiratoryRate;

    private String opponentTeam;
    private String type;
    private String location;
    private String weather;
    
    public HistoricalExtraDetailsResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, Double averageHeartRate, Double averageBodyTemperature, Double averageRespiratoryRate) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
        this.averageHeartRate = averageHeartRate;
        this.averageBodyTemperature = averageBodyTemperature;
        this.averageRespiratoryRate = averageRespiratoryRate;
    }

    public HistoricalExtraDetailsResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, Double averageHeartRate, Double averageBodyTemperature, Double averageRespiratoryRate, String opponentTeam, String type, String location, String weather) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
        this.averageHeartRate = averageHeartRate;
        this.averageBodyTemperature = averageBodyTemperature;
        this.averageRespiratoryRate = averageRespiratoryRate;
        this.opponentTeam = opponentTeam;
        this.type = type;
        this.location = location;
        this.weather = weather;
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

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    
}
