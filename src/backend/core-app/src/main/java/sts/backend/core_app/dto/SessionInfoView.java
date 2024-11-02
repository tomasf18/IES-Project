package sts.backend.core_app.dto;

import java.time.LocalDate;

import sts.backend.core_app.models.Match;

public interface SessionInfoView {

    Long getSessionId();

    LocalDate getStartTime();

    int getNumParticipants();

    String getState();

    Match getMatch();

}