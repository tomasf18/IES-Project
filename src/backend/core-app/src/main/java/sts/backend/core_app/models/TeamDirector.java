package sts.backend.core_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "teamDirectors")
public class TeamDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long directorId;

    @OneToOne
    @JoinColumn(name = "USER_FK", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    // standard constructors / setters / getters / toString
    public TeamDirector() {}

    public TeamDirector(User user, Team team) {
        this.user = user;
        this.team = team;  
    }

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
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
    
    @Override
    public String toString() {
        return "TeamDirector{" +
                "directorId=" + directorId +
                ", user=" + user +
                ", team=" + team +
                '}';
    }

    
}
