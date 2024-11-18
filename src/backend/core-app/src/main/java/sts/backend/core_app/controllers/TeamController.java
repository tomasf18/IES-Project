package sts.backend.core_app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.dto.team.RegistrationCodeString;
import sts.backend.core_app.dto.team.SensorPlayerInfo;
import sts.backend.core_app.dto.team.SensorPlayerView;
import sts.backend.core_app.dto.team.SensorTeamInfo;
import sts.backend.core_app.dto.team.TeamCreation;
import sts.backend.core_app.dto.team.TeamMemberRegistration;
import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.dto.team.TeamDirectorsView;
import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Sensor;
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
    @PreAuthorize("hasRole('ADMIN')")
    public Team api_create_team(@RequestBody TeamCreation teamCreation) throws ResourceNotFoundException {
        return teamService.createTeam(teamCreation);
    }

    @DeleteMapping("/team")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_delete_team(@RequestParam Long teamId) throws ResourceNotFoundException {
        teamService.deleteTeam(teamId); 
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/players-available/real-time-info")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToTeam(#teamId)")
    public List<PlayersAvailableRealTimeInfo> api_get_players_available_real_time_info(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getPlayersAvailableRealTimeInfo(teamId);
    }

    @PostMapping("/team/registration-code")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEAM_DIRECTOR') and @securityService.hasAccessToModerateTeam(#teamMemberRegistration.getTeamId()))")
    public RegistrationCode api_generate_new_registration_code(@RequestBody TeamMemberRegistration teamMemberRegistration) throws ResourceNotFoundException {
        return teamService.generateNewRegistrationCode(teamMemberRegistration);
    }

    @PutMapping("/team/registration-code/refresh")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEAM_DIRECTOR') and @securityService.hasAccessToModerateCode(#code.code))")
    public RegistrationCode api_refresh_registration_code(@RequestBody RegistrationCodeString code) throws ResourceNotFoundException {
        return teamService.refreshRegistrationCode(code);
    }

    @DeleteMapping("/team/registration-code")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEAM_DIRECTOR') and @securityService.hasAccessToModerateCode(#code.code))")
    public ResponseEntity<?> api_delete_registration_code(@RequestBody RegistrationCodeString code) throws ResourceNotFoundException {
        teamService.deleteRegistrationCode(code);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/team-members")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('TEAM_DIRECTOR') and @securityService.hasAccessToModerateTeam(#teamId))")
    public List<TeamMembersResponse> api_get_team_members(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getTeamMembers(teamId); // TODO: implement
    }

    @GetMapping("/team/teams-info")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<TeamsInfoView> api_get_teams_info() throws ResourceNotFoundException {
        return teamService.getTeamsInfo();
    }

    @GetMapping("/team/team-directors")
    @PreAuthorize("hasRole('ADMIN')")
    public Set<TeamDirectorsView> api_get_team_directors(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getTeamDirectors(teamId);
    }

    @GetMapping("/team/sensors")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSensorOfTeam(#teamId)")
    public Set<SensorPlayerView> api_get_sensors(@RequestParam Long teamId) throws ResourceNotFoundException {
        return teamService.getSensors(teamId);
    }

    @PostMapping("/team/sensors")
    @PreAuthorize("hasRole('ADMIN')")
    public Sensor api_assign_sensor(@RequestBody SensorTeamInfo sensorTeamInfo) throws ResourceNotFoundException {
        return teamService.assignSensor(sensorTeamInfo);
    }

    @DeleteMapping("/team/sensors")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_delete_sensors(@RequestParam Long sensorId) throws ResourceNotFoundException {
        teamService.deleteSensors(sensorId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/team/sensors/assign-player")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToModerateSensor(#sensorPlayerInfo.getSensorId())")
    public PlayerSensor api_assign_player_to_sensor(@RequestBody SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        return teamService.assignPlayerToSensor(sensorPlayerInfo);
    }

    @DeleteMapping("/team/sensors/assign-player")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToModerateSensor(#sensorPlayerInfo.getSensorId())")
    public ResponseEntity<?> api_unassign_player_from_sensor(@RequestBody SensorPlayerInfo sensorPlayerInfo) throws ResourceNotFoundException {
        teamService.unassignPlayerFromSensor(sensorPlayerInfo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/team/players-without-sensor")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSensorOfTeam(#teamId)")
    public ResponseEntity<?> getPlayersWithoutSensors(@RequestParam Long teamId) throws ResourceNotFoundException {
        List<Player> players = teamService.getPlayersWithoutSensorsByTeamId(teamId);

        // Transform the player data to only include name and playerId, as requested
        List<Map<String, Object>> response = players.stream().map(player -> {
            Map<String, Object> map = new HashMap<>();
            map.put("playerId", player.getUserId());
            map.put("name", player.getName());
            return map;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

