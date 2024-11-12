package sts.backend.core_app.dto.session;

public class NotificationResponse {
    private String playerName;
    private String message;

    public NotificationResponse(String playerName, String message) {
        this.playerName = playerName;
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
