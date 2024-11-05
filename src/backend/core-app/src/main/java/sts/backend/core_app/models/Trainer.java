package sts.backend.core_app.models;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "trainers")
public class Trainer extends User {

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    private Boolean isCoach;
    
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Session> sessions;

    // standard constructors / setters / getters / toString
    public Trainer() {}

    public Trainer(String name, String username, String email, String password, String profilePictureUrl, Team team, Boolean isCoach) {
        super(name, username, email, password, profilePictureUrl);
        this.team = team;
        this.isCoach = isCoach;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(Boolean isCoach) {
        this.isCoach = isCoach;
    }

    @Override
    public String toString() {
        return "Trainers{" +
                ", team=" + team +
                ", isCoach=" + isCoach +
                '}';
    }
}
