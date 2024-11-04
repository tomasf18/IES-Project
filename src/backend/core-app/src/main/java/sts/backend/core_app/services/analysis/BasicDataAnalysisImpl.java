package sts.backend.core_app.services.analysis;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.dto.team.SensorPlayerInfo;
import sts.backend.core_app.dto.team.SensorTeamInfo;
import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.PlayerSensorId;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Sensor;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;

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

    public Session getSessionById(Long sessionId) throws ResourceNotFoundException {
        return relationalQueries.getSessionById(sessionId);
    }

    // --- Get methods ---
    public Set<SessionInfoView> getSessionsInfoByTeamId(Team team) throws ResourceNotFoundException {
        return relationalQueries.getSessionsInfoByTeam(team);
    }

    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return relationalQueries.getRegistrationCode(code);
    }

    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException {
        return relationalQueries.getTeamsInfo();
    }

    public Set<SensorPlayerInfo> getSensors(Long teamId) throws ResourceNotFoundException {
        return relationalQueries.getSensors(teamId);
    }

    // --- Delete methods ---
    public void deleteRegistrationCode(RegistrationCode registrationCode) {
        relationalQueries.deleteRegistrationCode(registrationCode);
    }

    public void deleteSensor(Long sensorId) {
        relationalQueries.deleteSensor(sensorId);
    }

    public void unassignPlayerFromSensor(SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        PlayerSensor playerSensor = new PlayerSensor();
        playerSensor.setId(new PlayerSensorId(sensorPlayerInfo.getPlayerId(), sensorPlayerInfo.getSensorId()));
        playerSensor.setPlayer(relationalQueries.getPlayerById(sensorPlayerInfo.getPlayerId()));
        playerSensor.setSensor(relationalQueries.getSensorById(sensorPlayerInfo.getSensorId()));
        relationalQueries.deletePlayerSensor(playerSensor);
    }

    // --- Assign methods ---
    public Sensor assignSensor(SensorTeamInfo sensorTeamInfo) throws ResourceNotFoundException {
        Sensor sensor = new Sensor();
        sensor.setTeam(relationalQueries.getTeamById(sensorTeamInfo.getTeamId()));
        sensor.setSensorId(sensorTeamInfo.getSensorId());
        return relationalQueries.createSensor(sensor);
    }
    
    public PlayerSensor assignPlayerToSensor(SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        PlayerSensor playerSensor = new PlayerSensor();
        playerSensor.setId(new PlayerSensorId(sensorPlayerInfo.getPlayerId(), sensorPlayerInfo.getSensorId()));
        playerSensor.setPlayer(relationalQueries.getPlayerById(sensorPlayerInfo.getPlayerId()));
        playerSensor.setSensor(relationalQueries.getSensorById(sensorPlayerInfo.getSensorId()));
        return relationalQueries.createPlayerSensor(playerSensor);
    }
}
