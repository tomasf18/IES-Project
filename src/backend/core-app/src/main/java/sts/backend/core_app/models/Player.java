package sts.backend.core_app.models;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "players")
public class Player extends User {

    @ManyToOne
    @JoinColumn(name = "TEAM_FK")
    private Team team;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlayerSession> playerSessions;

    // standard constructors / setters / getters / toString
    public Player() {}

    public Player(String name, String username, String email, String password, String profilePictureUrl, Team team) {
        super(name, username, email, password, profilePictureUrl);
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
