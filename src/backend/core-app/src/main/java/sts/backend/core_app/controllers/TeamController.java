package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.RegistrationCodeString;
import sts.backend.core_app.dto.TeamCreation;
import sts.backend.core_app.dto.TeamMemberRegistration;
import sts.backend.core_app.dto.TeamsInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.services.business.TeamService;

@RestController
@RequestMapping("/api/v1")
public class TeamController {
    
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("/team")
    public Team api_create_team(@RequestBody TeamCreation teamCreation) throws ResourceNotFoundException {
        return teamService.createTeam(teamCreation);
    }

    @GetMapping("/team/registration-code")
    public RegistrationCode api_generate_new_registration_code(@RequestBody TeamMemberRegistration teamDirectorInfo) throws ResourceNotFoundException {
        // generate
        return teamService.generateNewRegistrationCode(teamDirectorInfo);
    }

    @PutMapping("/team/registration-code/refresh")
    public RegistrationCode api_refresh_registration_code(@RequestBody RegistrationCodeString code) throws ResourceNotFoundException {
        // refresh
        return teamService.refreshRegistrationCode(code);
    }

    @GetMapping("/team/teams-info")
    public Set<TeamsInfoView> api_get_teams_info() throws ResourceNotFoundException {
        return teamService.getTeamsInfo();
    }

}
