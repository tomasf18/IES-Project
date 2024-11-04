package sts.backend.core_app.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity(name = "playerSensors")
public class PlayerSensor {
    
    @EmbeddedId
    private PlayerSensorId id;

    @OneToOne
    @JoinColumn(name = "PLAYER_FK", insertable = false, updatable = false)
    private Player player;

    @OneToOne
    @JoinColumn(name = "SENSOR_FK", nullable = false)
    private Sensor sensor;
    
    // standard constructors / setters / getters / toString
    public PlayerSensor() {}

    public PlayerSensor(Player player, Sensor sensor) {
        this.player = player;
        this.sensor = sensor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
