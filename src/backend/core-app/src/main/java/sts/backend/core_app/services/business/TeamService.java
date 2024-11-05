package sts.backend.core_app.services.business;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.dto.team.RealTimeInfo;
import sts.backend.core_app.dto.team.RegistrationCodeString;
import sts.backend.core_app.dto.team.SensorPlayerInfo;
import sts.backend.core_app.dto.team.SensorPlayerView;
import sts.backend.core_app.dto.team.SensorTeamInfo;
import sts.backend.core_app.dto.team.TeamCreation;
import sts.backend.core_app.dto.team.TeamMemberRegistration;
import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Sensor;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;

@Service
public class TeamService {
    public static final int REGISTRATION_CODE_EXPIRATION_MINUTES = 15;

    private final BasicDataAnalysis basicDataAnalysis;

    public TeamService(BasicDataAnalysis basicDataAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public Team createTeam(TeamCreation teamCreation) throws ResourceNotFoundException {
        Team team = new Team();
        team.setName(teamCreation.getName());
        return basicDataAnalysis.createTeam(team);
    }

    public RegistrationCode generateNewRegistrationCode(TeamMemberRegistration teamMemberRegistration) throws ResourceNotFoundException {
        RegistrationCode registrationCode = new RegistrationCode();
        registrationCode.setTeam(basicDataAnalysis.getTeamById(teamMemberRegistration.getTeamId()));
        registrationCode.setName(teamMemberRegistration.getName());
        registrationCode.setCode(UUID.randomUUID().toString());
        registrationCode.setUserTypeId(teamMemberRegistration.getUserTypeId());
        registrationCode.setProfilePictureUrl(teamMemberRegistration.getProfilePictureUrl());
        
        registrationCode.setExpirationTime(LocalDateTime.now().plusMinutes(REGISTRATION_CODE_EXPIRATION_MINUTES));
        
        return basicDataAnalysis.createRegistrationCode(registrationCode);
    }

    public RegistrationCode refreshRegistrationCode(RegistrationCodeString code) throws ResourceNotFoundException {
        RegistrationCode registrationCode = basicDataAnalysis.getRegistrationCode(code.getCode());
        registrationCode.setCode(UUID.randomUUID().toString());
        registrationCode.setExpirationTime(LocalDateTime.now().plusMinutes(REGISTRATION_CODE_EXPIRATION_MINUTES));
        return basicDataAnalysis.createRegistrationCode(registrationCode);
    }
    
    public RegistrationCode claimRegistrationCode(String code) throws ResourceNotFoundException {
        RegistrationCode registrationCode = basicDataAnalysis.getRegistrationCode(code);

        if (registrationCode.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new ResourceNotFoundException("Registration code expired");
        }
        
        basicDataAnalysis.deleteRegistrationCode(registrationCode);

        return registrationCode;
    }

    public Set<TeamsInfoView> getTeamsInfo() throws ResourceNotFoundException {
        return basicDataAnalysis.getTeamsInfo();
    }

    public void deleteTeam(Long teamId) {
        basicDataAnalysis.deleteTeam(teamId);
    }

    public RealTimeInfo getPlayersAvailableRealTimeInfo(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersAvailableRealTimeInfo'");
    }

    public void deleteRegistrationCode(RegistrationCodeString code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRegistrationCode'");
    }

    public TeamMembersResponse getTeamMembers(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeamMembers'");
    }

    public TeamMembersResponse getTeamDirectors(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeamDirectors'");
    }

    public Set<SensorPlayerView> getSensors(Long teamId) throws ResourceNotFoundException {
        return basicDataAnalysis.getSensors(teamId);
    }

    public void deleteSensors(Long sensorId) {
        basicDataAnalysis.deleteSensor(sensorId);
    }

    public TeamMembersResponse getPlayersWithoutSensors(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersWithoutSensors'");
    }

    public Sensor assignSensor(SensorTeamInfo sensorTeamInfo) throws ResourceNotFoundException {
        return basicDataAnalysis.assignSensor(sensorTeamInfo);
    }

    public PlayerSensor assignPlayerToSensor(SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        return basicDataAnalysis.assignPlayerToSensor(sensorPlayerInfo);
    }

    public void unassignPlayerFromSensor(SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        basicDataAnalysis.unassignPlayerFromSensor(sensorPlayerInfo);
    }

}
