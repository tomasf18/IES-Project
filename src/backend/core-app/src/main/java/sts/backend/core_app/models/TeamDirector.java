package sts.backend.core_app.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class TeamDirector {
    @OneToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    public TeamDirector(User user, Team team) {
        this.user = user;
        this.team = team;
    }
}
