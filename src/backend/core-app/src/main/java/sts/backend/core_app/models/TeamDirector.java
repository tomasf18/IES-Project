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

    // standard constructors / setters / getters / toString
    public TeamDirector() {}

    public TeamDirector(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    
}
