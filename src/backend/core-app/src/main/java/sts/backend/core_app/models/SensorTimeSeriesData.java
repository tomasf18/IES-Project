package sts.backend.core_app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "sensorTimeSeriesData")
public class SensorTimeSeriesData {

    @EmbeddedId
    private SensorTimeSeriesDataId id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_FK", nullable = false)
    private Player player;

    @Column(nullable = false)
    private Double value;

    // standard constructors / setters / getters / toString
    public SensorTimeSeriesData() {}

    public SensorTimeSeriesData(SensorTimeSeriesDataId id, Player player, Double value) {
        this.id = id;
        this.player = player;
        this.value = value;
    }

    public SensorTimeSeriesDataId getId() {
        return id;
    }

    public void setId(SensorTimeSeriesDataId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SensorTimeSeriesData{" +
                "id=" + id +
                ", player=" + player +
                ", value=" + value +
                '}';
    }
}
