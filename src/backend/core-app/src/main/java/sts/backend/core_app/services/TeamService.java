package sts.backend.core_app.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.analysis.BasicDataAnalysisImpl;
import sts.backend.core_app.dto.TeamMemberRegistration;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;

@Service
public class TeamService {
    
    private final BasicDataAnalysisImpl basicDataAnalysis;

    public TeamService(BasicDataAnalysisImpl basicDataAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
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

    public RegistrationCode getRegistrationCode(String code) {
        return basicDataAnalysis.getRegistrationCode(code);
    }

}
