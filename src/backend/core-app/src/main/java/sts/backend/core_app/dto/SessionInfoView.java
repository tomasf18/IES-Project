package sts.backend.core_app.dto;

import java.time.LocalDate;

public interface SessionInfoView {

    Long getSessionId();

    Long getMatchId();

    LocalDate getStartDate();

    int getNumParticipants();

    String getState();

}