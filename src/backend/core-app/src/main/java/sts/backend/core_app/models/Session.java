package sts.backend.core_app.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "sessions")
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @NotBlank(message = "Session: start time is mandatory")
    private LocalDate startTime;

    private LocalDate endTime;

    @ManyToOne
    @JoinColumn(name = "trainerId", nullable = false)
    private Trainer trainer;

    @OneToOne
    @JoinColumn(name = "matchId", nullable = true)
    private Match match;

    // standard constructors / setters / getters / toString
    public Session() {}

    public Session(LocalDate startTime, LocalDate endTime, Trainer trainer, Match match) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainer = trainer;
        this.match = match;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", trainer=" + trainer +
                ", match=" + match +
                '}';
    }

}
