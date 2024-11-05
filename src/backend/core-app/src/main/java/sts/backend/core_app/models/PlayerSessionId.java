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

    @Override
    public String toString() {
        return "PlayerSessionId{" +
                "playerId=" + playerId +
                ", sessionId=" + sessionId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSessionId that = (PlayerSessionId) o;

        if (!playerId.equals(that.playerId)) return false;
        return sessionId.equals(that.sessionId);
    }

    @Override
    public int hashCode() {
        int result = playerId.hashCode();
        result = 31 * result + sessionId.hashCode();
        return result;
    }


}
