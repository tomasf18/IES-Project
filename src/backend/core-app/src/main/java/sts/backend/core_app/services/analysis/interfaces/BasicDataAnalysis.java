package sts.backend.core_app.services.analysis.interfaces;

import java.util.Set;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.dto.TeamsInfoView;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface BasicDataAnalysis {
    // --- Create methods ---
    public TeamDirector createTeamDirector(TeamDirector user);
    public Player createPlayer(Player user);
    public Trainer createTrainer(Trainer user);
    public Team createTeam(Team team);
    public Session createSession(Session session);
    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode);
    public Match createMatch(Match match);
    public User createAdministrator(User user);

    // --- Get by Id methods ---
    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException;
    public Team getTeamById(Long teamId) throws ResourceNotFoundException;
    public Session getSessionById(Long sessionId) throws ResourceNotFoundException;
    
    // --- Get methods ---
    public Set<SessionInfoView> getSessionsInfoByTeamId(Team team) throws ResourceNotFoundException;
    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException;
    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException;

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode);

}
