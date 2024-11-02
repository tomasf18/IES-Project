package sts.backend.core_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "trainers")
public class Trainer extends User {

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    // standard constructors / setters / getters / toString
    public Trainer() {}

    public Trainer(String name, String username, String email, String password, String profilePictureUrl, Team team) {
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
        return "Trainers{" +
                ", team=" + team +
                '}';
    }
}
