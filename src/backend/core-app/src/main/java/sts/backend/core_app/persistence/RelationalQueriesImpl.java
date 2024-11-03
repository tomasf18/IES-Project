package sts.backend.core_app.persistence;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.repositories.postgreDB.MatchRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.PlayerRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.PlayerSessionRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.RegistrationCodeRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.SessionRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TeamDirectorRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TeamRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TrainerRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.UserRepository;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.PlayerSession;
import sts.backend.core_app.models.PlayerSessionId;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;

@Service
public class RelationalQueriesImpl implements RelationalQueries {
    
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamDirectorRepository teamDirectorRepository;
    private final TrainerRepository trainerRepository;
    private final MatchRepository matchRepository;
    private final SessionRepository sessionRepository;
    private final PlayerSessionRepository playerSessionRepository;
    private final RegistrationCodeRepository registrationCodeRepository;

    public RelationalQueriesImpl(UserRepository userRepository, TeamRepository teamRepository, PlayerRepository playerRepository, TeamDirectorRepository teamDirectorRepository, TrainerRepository trainerRepository, MatchRepository matchRepository, SessionRepository sessionRepository, PlayerSessionRepository playerSessionRepository, RegistrationCodeRepository registrationCodeRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamDirectorRepository = teamDirectorRepository;
        this.trainerRepository = trainerRepository;
        this.matchRepository = matchRepository;
        this.sessionRepository = sessionRepository;
        this.playerSessionRepository = playerSessionRepository;
        this.registrationCodeRepository = registrationCodeRepository;
    }

    // --- Create methods ---

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Trainer createTrainer(Trainer trainer) {
        System.out.println("trainer: " + trainer);
        System.out.println("id: " + trainer.getUserId());
        return trainerRepository.save(trainer);
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode) {
        return registrationCodeRepository.save(registrationCode);
    }

    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    public TeamDirector createTeamDirector(TeamDirector teamDirector) {
        return teamDirectorRepository.save(teamDirector);
    }

    public PlayerSession createPlayerSession(PlayerSession playerSession) {
        return playerSessionRepository.save(playerSession);
    }

    public User createAdministrator(User user) {
        return userRepository.save(user);
    }

    // --- Get By ID methods ---

    public User getUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));
    }

    public Team getTeamById(Long teamId) throws ResourceNotFoundException {
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new ResourceNotFoundException("Team with ID " + teamId + " not found"));
    }

    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException {
        System.out.println("trainerId: " + userRepository.findTrainerByUserId(trainerId));
        return userRepository.findTrainerByUserId(trainerId)
            .orElseThrow(() -> new ResourceNotFoundException("Trainer with ID " + trainerId + " not found"));
    }

    public Match getMatchById(Long matchId) throws ResourceNotFoundException {
        return matchRepository.findById(matchId)
            .orElseThrow(() -> new ResourceNotFoundException("Match with ID " + matchId + " not found"));
    }

    public Session getSessionById(Long sessionId) throws ResourceNotFoundException {
        return sessionRepository.findById(sessionId)
            .orElseThrow(() -> new ResourceNotFoundException("Session with ID " + sessionId + " not found"));
    }

    public Player getPlayerById(Long playerId) throws ResourceNotFoundException {
        return playerRepository.findById(playerId)
            .orElseThrow(() -> new ResourceNotFoundException("Player with ID " + playerId + " not found"));
    }

    public TeamDirector getTeamDirectorById(Long teamDirectorId) throws ResourceNotFoundException {
        return teamDirectorRepository.findById(teamDirectorId)
            .orElseThrow(() -> new ResourceNotFoundException("TeamDirector with ID " + teamDirectorId + " not found"));
    }

    public PlayerSession getPlayerSessionById(PlayerSessionId playerSessionId) throws ResourceNotFoundException {
        return playerSessionRepository.findById(playerSessionId)
            .orElseThrow(() -> new ResourceNotFoundException("PlayerSession with ID " + playerSessionId + " not found"));
    }

    // --- Get By other methods ---

    public Set<SessionInfoView> getSessionsInfoByTeam(Team team) throws ResourceNotFoundException {
        return sessionRepository.findSessionInfoByTeam(team)
            .orElseThrow(() -> new ResourceNotFoundException("Sessions for team " + team.getName() + " not found"));
    }

    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return registrationCodeRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Registration code with code " + code + " not found"));
    }

    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException {
        return teamRepository.findAllTeamsInfo()
            .orElseThrow(() -> new ResourceNotFoundException("Teams info not found"));
    }

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode) {
        registrationCodeRepository.delete(registrationCode);
    }

    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
    
        // TODO: verify if is necessary to delete related entities
        // playerRepository.findById(userId).ifPresent(player -> {
        //     playerSessionRepository.deleteByPlayerId(player.getUserId());
        //     // playerSensorRepository.deleteByPlayerId(player.getId());
        //     playerRepository.delete(player);
        // });
    
        userRepository.deleteById(userId); 
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

}
