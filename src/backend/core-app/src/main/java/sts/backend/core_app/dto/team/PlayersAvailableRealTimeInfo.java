package sts.backend.core_app.dto.team;

import java.util.List;

import sts.backend.core_app.dto.player.ValueTimeSeriesView;

public class PlayersAvailableRealTimeInfo {
    private String playerName;
    private String playerProfilePictureUrl;
    private List<ValueTimeSeriesView> heartRateData;
    private Double heartRate;

    public PlayersAvailableRealTimeInfo(String playerName, String playerProfilePictureUrl, List<ValueTimeSeriesView> heartRateData, Double heartRate) {
        this.playerName = playerName;
        this.playerProfilePictureUrl = playerProfilePictureUrl;
        this.heartRateData = heartRateData;
        this.heartRate = heartRate;
    }

    // Getters and Setters

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerProfilePictureUrl() {
        return playerProfilePictureUrl;
    }

    public void setPlayerProfilePictureUrl(String playerProfilePictureUrl) {
        this.playerProfilePictureUrl = playerProfilePictureUrl;
    }

    public Double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public List<ValueTimeSeriesView> getHeartRateData() {
        return heartRateData;
    }

    public void setHeartRateData(List<ValueTimeSeriesView> heartRateData) {
        this.heartRateData = heartRateData;
    }
}
