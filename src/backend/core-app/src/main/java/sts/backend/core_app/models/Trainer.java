package sts.backend.core_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "trainers")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;

    @OneToOne
    @JoinColumn(name = "USER_FK", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    // standard constructors / setters / getters / toString
    public Trainer() {}

    public Trainer(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    public Long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
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
        return "Trainers{" +
                "trainerId=" + trainerId +
                ", user=" + user +
                ", team=" + team +
                '}';
    }
}
