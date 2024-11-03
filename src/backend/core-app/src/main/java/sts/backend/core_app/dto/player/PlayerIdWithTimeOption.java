package sts.backend.core_app.dto.player;

public class PlayerIdWithTimeOption {
    
    private Long playerId;
    private Long timeOptionId;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getTimeOptionId() {
        return timeOptionId;
    }

    public void setTimeOptionId(Long timeOptionId) {
        this.timeOptionId = timeOptionId;
    }
    
}
