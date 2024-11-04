package sts.backend.core_app.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GenerationType;

@Entity(name = "sensors")
public class Sensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;
    
    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    @OneToOne(mappedBy = "sensor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PlayerSensor playerSensor;

    // standard constructors, getters, setters, etc.
    public Sensor() {}

    public Sensor(Team team) {
        this.team = team;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                ", team=" + team +
                '}';
    }
}
