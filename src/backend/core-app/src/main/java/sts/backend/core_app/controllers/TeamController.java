package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.TeamCreation;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.services.TeamService;

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

}
