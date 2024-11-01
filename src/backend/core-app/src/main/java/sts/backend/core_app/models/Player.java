package sts.backend.core_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "players")
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;

    @ManyToOne
    @NotBlank(message = "Player: user is mandatory")
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @NotBlank(message = "Player: team is mandatory")
    @JoinColumn(name = "teamId")
    private Team team;
    
    @Column(unique=true)
    private Long activeSensorId;

    // standard constructors / setters / getters / toString
    public Player() {}

    public Player(User user, Team team, Long activeSensorId) {
        this.user = user;
        this.team = team;
        this.activeSensorId = activeSensorId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "playerId=" + playerId +
                ", user=" + user +
                ", team=" + team +
                ", activeSensorId=" + activeSensorId +
                '}';
    }

}
