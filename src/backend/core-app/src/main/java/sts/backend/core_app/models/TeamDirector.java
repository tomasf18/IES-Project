package sts.backend.core_app.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "teamDirectors")
public class TeamDirector extends User {

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    // standard constructors / setters / getters / toString
    public TeamDirector() {}

    public TeamDirector(String name, String username, String email, String password, String profilePictureUrl, Set<String> roles, Team team) {
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
        return "TeamDirector{" +
                ", team=" + team +
                '}';
    }

    
}
