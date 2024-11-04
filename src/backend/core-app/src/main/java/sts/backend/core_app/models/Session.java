package sts.backend.core_app.models;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "sessions")
@Inheritance(strategy = InheritanceType.JOINED)
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @NotBlank(message = "Session: name is mandatory")
    @Size(max = 50, message = "Session: name must be at most 50 characters")
    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "session", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PlayerSession> playerSessions;

    @ManyToOne
    @JoinColumn(name = "TRAINER_FK", nullable = false)
    private Trainer trainer;

    // standard constructors / setters / getters / toString
    public Session() {}

    public Session(String name, LocalDateTime startTime, LocalDateTime endTime, Trainer trainer) {
        this.startTime = startTime;
        this.name = name;
        this.endTime = endTime;
        this.trainer = trainer;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", trainer=" + trainer +
                '}';
    }

}
