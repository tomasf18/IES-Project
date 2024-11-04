package sts.backend.core_app.persistence.interfaces;

import java.util.Set;

import sts.backend.core_app.models.User;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.dto.team.SensorPlayerInfo;
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

public interface RelationalQueries {
    // --- Create methods ---
    public User createUser(User user);
    public Team createTeam(Team team);
    public Trainer createTrainer(Trainer trainer);
    public Match createMatch(Match match);
    public Session createSession(Session session);
    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode);
    public Player createPlayer(Player player);
    public TeamDirector createTeamDirector(TeamDirector teamDirector);
    public PlayerSession createPlayerSession(PlayerSession playerSession);
    public User createAdministrator(User user);
    public Sensor createSensor(Sensor sensor);
    public PlayerSensor createPlayerSensor(PlayerSensor playerSensor);

    // -- Get by Id methods ---
    public User getUserById(Long userId) throws ResourceNotFoundException;
    public Team getTeamById(Long teamId) throws ResourceNotFoundException;
    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException;
    public Match getMatchById(Long matchId) throws ResourceNotFoundException;
    public Session getSessionById(Long sessionId) throws ResourceNotFoundException;
    public Player getPlayerById(Long playerId) throws ResourceNotFoundException;
    public TeamDirector getTeamDirectorById(Long teamDirectorId) throws ResourceNotFoundException;
    public PlayerSession getPlayerSessionById(PlayerSessionId playerSessionId) throws ResourceNotFoundException;
    public Sensor getSensorById(Long sensorId) throws ResourceNotFoundException;

    // --- Get methods ---
    public Set<SessionInfoView> getSessionsInfoByTeam(Team team) throws ResourceNotFoundException;
    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException;
    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException;
    public Set<SensorPlayerInfo> getSensors(Long teamId) throws ResourceNotFoundException;

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode);
    public void deleteSensor(Long sensorId);
    public void deletePlayerSensor(PlayerSensor playerSensor);    
    
}
