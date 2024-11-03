package sts.backend.core_app.analysis;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.analysis.interfaces.BasicDataAnalysis;
import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;

@Service
public class BasicDataAnalysisImpl implements BasicDataAnalysis{
    
    private final RelationalQueries relationalQueries;

    public BasicDataAnalysisImpl(RelationalQueries relationalQueries) {
        this.relationalQueries = relationalQueries;
    }

    // --- Create methods ---

    public TeamDirector createTeamDirector(TeamDirector user) {
        return relationalQueries.createTeamDirector(user);
    }

    public Player createPlayer(Player user) {
        return relationalQueries.createPlayer(user);
    }

    public Trainer createTrainer(Trainer user) {
        return relationalQueries.createTrainer(user);
    }

    public Team createTeam(Team team) {
        return relationalQueries.createTeam(team);
    }
    
    public Session createSession(Session session) {
        return relationalQueries.createSession(session);
    }

    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode) {
        return relationalQueries.createRegistrationCode(registrationCode);
    }

    public Match createMatch(Match match) {
        return relationalQueries.createMatch(match);
    }

    public User createAdministrator(User user) {
        return relationalQueries.createAdministrator(user);
    }

    // --- Get by Id methods ---

    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException {
        return relationalQueries.getTrainerById(trainerId);
    }

    public Team getTeamById(Long teamId) throws ResourceNotFoundException {
        return relationalQueries.getTeamById(teamId);
    }

    public Set<SessionInfoView> getSessionsInfoByTeamId(Team team) throws ResourceNotFoundException {
        return relationalQueries.getSessionsInfoByTeam(team);
    }

    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return relationalQueries.getRegistrationCode(code);
    }

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode) {
        relationalQueries.deleteRegistrationCode(registrationCode);
    }

}
