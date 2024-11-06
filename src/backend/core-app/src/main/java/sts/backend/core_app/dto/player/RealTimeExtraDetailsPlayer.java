package sts.backend.core_app.dto.player;

import java.util.List;


public class RealTimeExtraDetailsPlayer {

    private List<ValueTimeSeriesView> heartRateData;
    private List<ValueTimeSeriesView> bodyTemperatureData;
    private List<ValueTimeSeriesView> respiratoryRateData;

    private Long playerId;
    private String playerName;
    // Getters and Setters

    public List<ValueTimeSeriesView> getHeartRateData() {
        return heartRateData;
    }

    public void setHeartRateData(List<ValueTimeSeriesView> heartRateData) {
        this.heartRateData = heartRateData;
    }

    public List<ValueTimeSeriesView> getBodyTemperatureData() {
        return bodyTemperatureData;
    }

    public void setBodyTemperatureData(List<ValueTimeSeriesView> bodyTemperatureData) {
        this.bodyTemperatureData = bodyTemperatureData;
    }

    public List<ValueTimeSeriesView> getRespiratoryRateData() {
        return respiratoryRateData;
    }

    public void setRespiratoryRateData(List<ValueTimeSeriesView> respiratoryRateData) {
        this.respiratoryRateData = respiratoryRateData;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }
}