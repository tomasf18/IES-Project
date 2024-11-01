package sts.backend.core_app.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

public class Trainers {
    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    // standard constructors / setters / getters / toString
    public Trainers() {}

    public Trainers(User user, Team team) {
        this.user = user;
        this.team = team;
    }
}
