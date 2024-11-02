package sts.backend.core_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "players")
public class Player extends User {

    @ManyToOne
    @NotBlank(message = "Player: team is mandatory")
    @JoinColumn(name = "TEAM_FK")
    private Team team;
    
    @Column(unique=true)
    private Long activeSensorId;

    // standard constructors / setters / getters / toString
    public Player() {}

    public Player(String name, String username, String email, String password, String profilePictureUrl, Team team, Long activeSensorId) {
        super(name, username, email, password, profilePictureUrl);
        this.team = team;
        this.activeSensorId = activeSensorId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getActiveSensorId() {
        return activeSensorId;
    }

    public void setActiveSensorId(Long activeSensorId) {
        this.activeSensorId = activeSensorId;
    }

    @Override
    public String toString() {
        return "Player{" +
                ", team=" + team +
                ", activeSensorId=" + activeSensorId +
                '}';
    }

}
