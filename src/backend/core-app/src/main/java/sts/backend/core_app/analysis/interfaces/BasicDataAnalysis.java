package sts.backend.core_app.analysis.interfaces;

import java.util.Set;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface BasicDataAnalysis {
    public Set<SessionInfoView> getSessionsInfoByTeamId(Team team);
    public Session createSession(Session session);
    public Session createMatch(Match match, Session session);
    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException;
    public Team getTeamById(Long teamId) throws ResourceNotFoundException;
    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode);
    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException;
}
