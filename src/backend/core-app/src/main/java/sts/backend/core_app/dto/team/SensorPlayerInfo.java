package sts.backend.core_app.dto.team;

public class SensorPlayerInfo {
    private Long sensorId;
    private Long playerId;

    public Long getSensorId() {
        return sensorId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
