package sts.backend.core_app.dto.session;

import java.time.LocalDateTime;

public interface SessionInfoView {
    String getSessionName();
    Long getSessionId();
    LocalDateTime getStartTime();
    LocalDateTime getEndTime();
    int getNumParticipants();
    String getState();
    boolean getIsMatch();
}