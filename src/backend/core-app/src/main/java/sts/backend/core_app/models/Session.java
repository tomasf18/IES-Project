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
import jakarta.validation.constraints.Size;

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
    private Trainer player;

    @OneToOne
    @JoinColumn(name = "matchId", nullable = true)
    private Match match;

    // standard constructors / setters / getters / toString
    public Session() {}

    public Session(String startTime, String endTime, Trainer player, Match match) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.player = player;
        this.match = match;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Trainer getPlayer() {
        return player;
    }

    public void setPlayer(Trainer player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

}
