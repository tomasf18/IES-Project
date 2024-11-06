package sts.backend.core_app.dto.session;

import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;

public class HistoricalInfoResponse {
    
    private String sessionName;
    private String date;
    private int time;
    private int participants;
    private List<RealTimeExtraDetailsPlayer> historicalDataPlayers;

    public HistoricalInfoResponse() {
    }
    
    public HistoricalInfoResponse(String sessionName, String date, int time, int participants, List<RealTimeExtraDetailsPlayer> historicalDataPlayers) {
        this.sessionName = sessionName;
        this.date = date;
        this.time = time;
        this.participants = participants;
        this.historicalDataPlayers = historicalDataPlayers;
    }

    // TODO: If match ...


    // Getters and Setters

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public List<RealTimeExtraDetailsPlayer> getHistoricalDataPlayers() {
        return historicalDataPlayers;
    }

    public void setHistoricalDataPlayers(List<RealTimeExtraDetailsPlayer> historicalDataPlayers) {
        this.historicalDataPlayers = historicalDataPlayers;
    }
}
