package sts.backend.core_app.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.analysis.interfaces.BasicDataAnalysis;
import sts.backend.core_app.dto.TeamCreation;
import sts.backend.core_app.dto.TeamMemberRegistration;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Team;

@Service
public class TeamService {
    
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
        registrationCode.setCode(UUID.randomUUID().toString());
        registrationCode.setUserTypeId(teamMemberRegistration.getUserTypeId());
        registrationCode.setProfilePictureUrl(teamMemberRegistration.getProfilePictureUrl());
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15); // 15 minutes to expire
        registrationCode.setExpirationTime(LocalDate.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()));
        
        return basicDataAnalysis.createRegistrationCode(registrationCode);
    }
    
    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return basicDataAnalysis.getRegistrationCode(code);
    }

}
