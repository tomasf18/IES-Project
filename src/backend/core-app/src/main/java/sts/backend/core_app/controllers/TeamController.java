package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.IdLong;
import sts.backend.core_app.dto.team.RealTimeInfo;
import sts.backend.core_app.dto.team.RegistrationCodeString;
import sts.backend.core_app.dto.team.SensorsResponse;
import sts.backend.core_app.dto.team.TeamCreation;
import sts.backend.core_app.dto.team.TeamMemberRegistration;
import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.dto.team.TeamsInfoView;
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

    @DeleteMapping("/team")
    public ResponseEntity<?> api_delete_team(@RequestParam Long teamId) throws ResourceNotFoundException {
        teamService.deleteTeam(teamId); // TODO: implement
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/players-available/real-time-info")
    public RealTimeInfo api_get_players_available_real_time_info(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getPlayersAvailableRealTimeInfo(teamId);
    }

    @PostMapping("/team/registration-code")
    public RegistrationCode api_generate_new_registration_code(@RequestBody TeamMemberRegistration teamMemberRegistration) throws ResourceNotFoundException {
        return teamService.generateNewRegistrationCode(teamMemberRegistration);
    }

    @PutMapping("/team/registration-code/refresh")
    public RegistrationCode api_refresh_registration_code(@RequestBody RegistrationCodeString code) throws ResourceNotFoundException {
        return teamService.refreshRegistrationCode(code);
    }

    @DeleteMapping("/team/registration-code")
    public ResponseEntity<?> api_delete_registration_code(@RequestBody RegistrationCodeString code) throws ResourceNotFoundException {
        teamService.deleteRegistrationCode(code); // TODO: implement
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/team-members")
    public TeamMembersResponse api_get_team_members(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getTeamMembers(teamId); // TODO: implement
    }

    @GetMapping("/team/teams-info")
    public Set<TeamsInfoView> api_get_teams_info() throws ResourceNotFoundException {
        return teamService.getTeamsInfo();
    }

    @GetMapping("/team/team-directors")
    public TeamMembersResponse api_get_team_directors(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getTeamDirectors(teamId); // TODO: implement
    }

    @GetMapping("/team/sensors")
    public SensorsResponse api_get_sensors(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getSensors(teamId); // TODO: implement
    }

    // TODO: Sensors is not implemented yet
    // @PostMapping("/team/sensors")
    // public Sensor api_set_sensors(@RequestBody Long sensorId) throws ResourceNotFoundException {
    //     return teamService.setSensors(sensorId); // TODO: implement
    // }

    @DeleteMapping("/team/sensors")
    public ResponseEntity<?> api_delete_sensors(@RequestParam Long sensorId) throws ResourceNotFoundException {
        teamService.deleteSensors(sensorId); // TODO: implement
        return ResponseEntity.ok().build();
    }

    // TODO: Sensors is not implemented yet
    // @PostMapping("/team/sensors/assign-player")
    // public Sensor api_assign_player_to_sensor(@RequestBody SensorAssignment sensorAssignment) throws ResourceNotFoundException {
    //     return teamService.assignPlayerToSensor(sensorAssignment); // TODO: implement
    // }

    @GetMapping("/team/players-without-sensors")
    public TeamMembersResponse api_get_players_without_sensors(@RequestBody IdLong teamId) throws ResourceNotFoundException {
        return teamService.getPlayersWithoutSensors(teamId); // TODO: implement
    }


}
