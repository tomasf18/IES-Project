package sts.backend.core_app.dto.session;

import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;

public class RealTimeInfoResponse extends HistoricalInfoResponse {
    private String opponentTeam;
    private String type;
    private String location;
    private String weather;

    // GET /sessions/real-time-info 
    public RealTimeInfoResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
    }

    // GET /sessions/real-time-info (match) 
    public RealTimeInfoResponse(Long sessionId, String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers, String opponentTeam, String type, String location, String weather) {
        super(sessionId, sessionName, date, time, participants, historicalDataPlayers);
        this.opponentTeam = opponentTeam;
        this.type = type;
        this.location = location;
        this.weather = weather;
    }

    // Getters and Setters

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


