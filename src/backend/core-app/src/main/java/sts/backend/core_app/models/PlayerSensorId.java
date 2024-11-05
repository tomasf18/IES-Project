package sts.backend.core_app.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerSensorId implements Serializable {
    // Composite key for PlayerSensor
    
    private Long playerId;
    private Long sensorId;

    // standard constructors / setters / getters / toString
    public PlayerSensorId() {}

    public PlayerSensorId(Long playerId, Long sensorId) {
        this.playerId = playerId;
        this.sensorId = sensorId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "PlayerSensorId{" +
                "playerId=" + playerId +
                ", sensorId=" + sensorId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSensorId that = (PlayerSensorId) o;

        if (!playerId.equals(that.playerId)) return false;
        return sensorId.equals(that.sensorId);
    }

    @Override
    public int hashCode() {
        int result = playerId.hashCode();
        result = 31 * result + sensorId.hashCode();
        return result;
    }
}
