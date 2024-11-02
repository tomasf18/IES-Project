package sts.backend.core_app.persistence;

import java.util.Set;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.Trainer;

public interface RelationalQueries {
    public Set<SessionInfoView> getSessionsInfoByTeam(Team team);
    public Session createSession(Session result);
    public Match createMatch(Match match);
    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException;
    public Team getTeamById(Long teamId) throws ResourceNotFoundException;
    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode);
    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException;
}
