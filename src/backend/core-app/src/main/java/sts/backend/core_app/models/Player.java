package sts.backend.core_app.models;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "players")
public class Player extends User {

    @ManyToOne
    @JoinColumn(name = "TEAM_FK")
    private Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlayerSession> playerSessions;

    @OneToOne(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private PlayerSensor playerSensor;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<SensorTimeSeriesData> sensorTimeSeriesData;

    // standard constructors / setters / getters / toString
    public Player() {}

    public Player(String name, String username, String email, String password, String profilePictureUrl, Set<String> roles, Team team) {
        super(name, username, email, password, profilePictureUrl, roles);
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", team=" + team +
                '}';
    }

}
