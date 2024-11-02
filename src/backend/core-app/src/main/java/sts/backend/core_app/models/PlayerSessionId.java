package sts.backend.core_app.models;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PlayerSessionId implements Serializable {
    // Composite key for PlayerSession
    
    private Long playerId;
    private Long sessionId;

    // standard constructors / setters / getters / toString
    public PlayerSessionId() {}

    public PlayerSessionId(Long playerId, Long sessionId) {
        this.playerId = playerId;
        this.sessionId = sessionId;
    }

    // Getters and setters

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
