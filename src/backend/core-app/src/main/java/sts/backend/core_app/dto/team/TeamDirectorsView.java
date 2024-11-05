package sts.backend.core_app.dto.team;

public interface TeamDirectorsView {
    Long getTeamDirectorId();
    String getName();
    Boolean getIsOfficialMember();
    String getRegistrationCode();
}