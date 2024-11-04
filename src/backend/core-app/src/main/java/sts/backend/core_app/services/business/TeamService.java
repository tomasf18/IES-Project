package sts.backend.core_app.services.business;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.dto.team.RealTimeInfo;
import sts.backend.core_app.dto.team.RegistrationCodeString;
import sts.backend.core_app.dto.team.SensorsResponse;
import sts.backend.core_app.dto.team.TeamCreation;
import sts.backend.core_app.dto.team.TeamMemberRegistration;
import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTeam'");
    }

    public RealTimeInfo getPlayersAvailableRealTimeInfo(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersAvailableRealTimeInfo'");
    }

    public void deleteRegistrationCode(RegistrationCodeString code) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRegistrationCode'");
    }

    public List<TeamMembersResponse> getTeamMembers(Long teamId) {
        return basicDataAnalysis.getTeamMembers(teamId);
    }

    public TeamMembersResponse getTeamDirectors(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTeamDirectors'");
    }

    public SensorsResponse getSensors(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSensors'");
    }

    public void deleteSensors(Long sensorId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSensors'");
    }

    public TeamMembersResponse getPlayersWithoutSensors(Long teamId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayersWithoutSensors'");
    }

}
