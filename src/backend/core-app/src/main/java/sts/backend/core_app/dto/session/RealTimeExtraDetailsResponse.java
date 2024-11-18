package sts.backend.core_app.dto.session;

import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;

public class RealTimeExtraDetailsResponse extends HistoricalInfoResponse {
    private Double lastHeartRate;
    private Double lastBodyTemperature;
    private Double lastRespiratoryRate;

    private String opponentTeam;
    private String type;
    private String location;
    private String weather;
    
    // GET /sessions/real-time-extra-details
    public RealTimeExtraDetailsResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, Double lastHeartRate, Double lastBodyTemperature, Double lastRespiratoryRate) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
        this.lastHeartRate = lastHeartRate;
        this.lastBodyTemperature = lastBodyTemperature;
        this.lastRespiratoryRate = lastRespiratoryRate;
    }

    // GET /sessions/real-time-extra-details
    public RealTimeExtraDetailsResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, Double lastHeartRate, Double lastBodyTemperature, Double lastRespiratoryRate, String opponentTeam, String type, String location, String weather) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
        this.lastHeartRate = lastHeartRate;
        this.lastBodyTemperature = lastBodyTemperature;
        this.lastRespiratoryRate = lastRespiratoryRate;
        this.opponentTeam = opponentTeam;
        this.type = type;
        this.location = location;
        this.weather = weather;
    }

    public Double getLastHeartRate() {
        return lastHeartRate;
    }

    public void setLastHeartRate(Double lastHeartRate) {
        this.lastHeartRate = lastHeartRate;
    }

    public Double getLastBodyTemperature() {
        return lastBodyTemperature;
    }

    public void setLastBodyTemperature(Double lastBodyTemperature) {
        this.lastBodyTemperature = lastBodyTemperature;
    }

    public Double getLastRespiratoryRate() {
        return lastRespiratoryRate;
    }

    public void setLastRespiratoryRate(Double lastRespiratoryRate) {
        this.lastRespiratoryRate = lastRespiratoryRate;
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
