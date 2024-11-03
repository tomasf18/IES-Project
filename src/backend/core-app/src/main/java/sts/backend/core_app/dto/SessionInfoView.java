package sts.backend.core_app.dto;

import java.time.LocalDateTime;

import sts.backend.core_app.models.Match;

public interface SessionInfoView {
    Long getSessionId();
    LocalDateTime getStartTime();
    int getNumParticipants();
    String getState();
    Match getMatch();
}