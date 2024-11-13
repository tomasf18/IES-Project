package sts.backend.core_app.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.repositories.postgreDB.MatchRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.PlayerRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.PlayerSensorRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.PlayerSessionRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.RegistrationCodeRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.SensorRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.SessionRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TeamDirectorRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TeamRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.TrainerRepository;
import sts.backend.core_app.persistence.repositories.postgreDB.UserRepository;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.dto.team.TeamDirectorsView;
import sts.backend.core_app.dto.team.SensorPlayerView;
import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.PlayerSession;
import sts.backend.core_app.models.PlayerSessionId;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Sensor;
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
    private final SensorRepository sensorRepository;
    private final PlayerSensorRepository playerSensorRepository;

    public RelationalQueriesImpl(UserRepository userRepository, TeamRepository teamRepository, PlayerRepository playerRepository, TeamDirectorRepository teamDirectorRepository, TrainerRepository trainerRepository, MatchRepository matchRepository, SessionRepository sessionRepository, PlayerSessionRepository playerSessionRepository, RegistrationCodeRepository registrationCodeRepository, SensorRepository sensorRepository, PlayerSensorRepository playerSensorRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamDirectorRepository = teamDirectorRepository;
        this.trainerRepository = trainerRepository;
        this.matchRepository = matchRepository;
        this.sessionRepository = sessionRepository;
        this.playerSessionRepository = playerSessionRepository;
        this.registrationCodeRepository = registrationCodeRepository;
        this.sensorRepository = sensorRepository;
        this.playerSensorRepository = playerSensorRepository;
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

    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public PlayerSensor createPlayerSensor(PlayerSensor playerSensor) {
        return playerSensorRepository.save(playerSensor);
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

    public Sensor getSensorById(Long sensorId) throws ResourceNotFoundException {
        return sensorRepository.findById(sensorId)
            .orElseThrow(() -> new ResourceNotFoundException("Sensor with ID " + sensorId + " not found"));
    }

    // --- Get By other methods ---

    public Set<SessionInfoView> getSessionsInfoByTeam(Team team) throws ResourceNotFoundException {
        return sessionRepository.findSessionInfoByTeam(team)
            .orElseThrow(() -> new ResourceNotFoundException("Sessions for team " + team.getName() + " not found"));
    }

    public Set<SessionInfoView> getSessionsInfoByPlayerId(Long playerId) throws ResourceNotFoundException {
        return sessionRepository.findSessionInfoByPlayerId(playerId)
            .orElseThrow(() -> new ResourceNotFoundException("Sessions for player with ID " + playerId + " not found"));
    }

    public Set<Player> getPlayersInSessionBySessionId(Long sessionId) throws ResourceNotFoundException {
        return playerRepository.findByPlayerSessionsSessionSessionId(sessionId)
            .orElseThrow(() -> new ResourceNotFoundException("Players in session with ID " + sessionId + " not found"));
    }

    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return registrationCodeRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Registration code with code " + code + " not found"));
    }

    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException {
        return teamRepository.findAllTeamsInfo()
            .orElseThrow(() -> new ResourceNotFoundException("Teams info not found"));
    }

    public Set<TeamDirectorsView> getTeamDirectors(Long teamId) throws ResourceNotFoundException {
        Set<TeamDirectorsView> officialDirectors = teamDirectorRepository.findTeamDirectorsByTeamId(teamId)
            .orElseThrow(() -> new ResourceNotFoundException("No official team directors found for team with ID " + teamId));

        Set<TeamDirectorsView> pendingDirectors = teamDirectorRepository.findPendingTeamDirectorsByTeamId(teamId)
            .orElse(Set.of());

        officialDirectors.addAll(pendingDirectors);

        if (officialDirectors.isEmpty()) {
            throw new ResourceNotFoundException("No team directors found for team with ID " + teamId);
        }

        return officialDirectors;
    }    

    public Set<SensorPlayerView> getSensors(Long teamId) throws ResourceNotFoundException {
        return teamRepository.findSensorsWithPlayersByTeamId(teamId)
            .orElseThrow(() -> new ResourceNotFoundException("Sensors for team with ID " + teamId + " not found"));
    }
    
            public List<User> getUsers() throws ResourceNotFoundException {
        return userRepository.findAll();
    }

    public List<Player> getPlayersWithoutSensorsByTeamId(Long teamId) throws ResourceNotFoundException {
        return playerRepository.findByTeamTeamIdAndPlayerSensorIsNull(teamId);
    }

    public List<Player> getAvailablePlayersByTeamId(Long teamId) throws ResourceNotFoundException {
        List<Player> result = new ArrayList<>();
        List<Player> playersWithSensor = playerRepository.findByTeamTeamIdAndPlayerSensorIsNotNull(teamId);
        for (Player player : playersWithSensor) {
            Optional<Set<SessionInfoView>> playerSessions = sessionRepository.findSessionInfoByPlayerId(player.getUserId());
            
            if (playerSessions.isEmpty() || 
                playerSessions.get().stream().allMatch(session -> session.getState().equals("Closed"))) {
                result.add(player);
            }
        }
        return result;
    }

    public Player getPlayerByUsername(String username) throws ResourceNotFoundException {
        return playerRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Player with username " + username + " not found"));
    }

    public Trainer getTrainerByUsername(String username) throws ResourceNotFoundException {
        return trainerRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Trainer with username " + username + " not found"));
    }

    public TeamDirector getTeamDirectorByUsername(String username) throws ResourceNotFoundException {
        return teamDirectorRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Team director with username " + username + " not found"));
    }

    public User getUserByUsername(String currentUsername) throws ResourceNotFoundException {
        return userRepository.findByUsername(currentUsername)
            .orElseThrow(() -> new ResourceNotFoundException("User with username " + currentUsername + " not found"));
    }

    public User getUserByEmail(String email) throws ResourceNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
    }

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode) {
        registrationCodeRepository.delete(registrationCode);
    }

    @Override
    public List<TeamMembersResponse> getTeamMembers(Long teamId) {
        List<TeamMembersResponse> teamMembers = new ArrayList<>();

        // Get players
        List<Player> players = playerRepository.findPlayersByTeamTeamId(teamId);
        teamMembers.addAll(players.stream()
            .map(player -> new TeamMembersResponse(player.getUserId(), player.getName(), player.getProfilePictureUrl(), 1L, null))
            .collect(Collectors.toList()));    

        // Get Coaches
        List<Trainer> coaches = trainerRepository.findByTeamTeamIdAndIsCoachTrue(teamId);
        teamMembers.addAll(coaches.stream()
            .map(trainer -> new TeamMembersResponse(trainer.getUserId(), trainer.getName(), trainer.getProfilePictureUrl(), 3L, null))
            .collect(Collectors.toList()));

        // Get Personal Trainers
        List<Trainer> personalTrainers = trainerRepository.findByTeamTeamIdAndIsCoachFalse(teamId);
        teamMembers.addAll(personalTrainers.stream()
            .map(trainer -> new TeamMembersResponse(trainer.getUserId(), trainer.getName(), trainer.getProfilePictureUrl(), 4L, null))
            .collect(Collectors.toList()));    

        // Pending

        List<TeamMembersResponse> teamMembersResponses = teamRepository.findPendingUsersByTypeId(teamId, Set.of(1L,3L,4L));
        teamMembers.addAll(teamMembersResponses);

        return teamMembers;
    } 

    public void deleteSensor(Long sensorId) {
        sensorRepository.deleteById(sensorId);
    }

    public void deletePlayerSensor(PlayerSensor playerSensor) {
        playerSensorRepository.delete(playerSensor);
    }
    
    @Override
    public void deleteUser(Long userId) throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        
        playerRepository.deleteById(userId);
        teamDirectorRepository.deleteById(userId);
        trainerRepository.deleteById(userId);
        userRepository.deleteById(userId); 
    }

    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    @Override
    public List<Long> getPlayerIdsBySessionId(Long sessionId) {
        return playerSessionRepository.findPlayerUserIdsBySessionSessionId(sessionId);
    }

}
