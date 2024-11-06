package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import sts.backend.core_app.dto.player.SessionsAllDayOfYear;
import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.services.business.PlayerService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/sessions/all-days-of-year")
    @PreAuthorize("@securityService.hasAccessToUser(#playerId)")
    public SessionsAllDayOfYear api_get_player_fatigue_all_days_of_year(@RequestParam Long playerId, @RequestParam Long year) {
        return playerService.getPlayerSessionsAllDaysOfYear(playerId, year); // TODO: implement
    }

    @PostMapping("/player/add-metric-value")
    @PreAuthorize("@securityService.hasAccessToUser(#metricValue.playerId)")
    public SensorTimeSeriesData api_add_metric_value(@RequestBody MetricValue metricValue) throws ResourceNotFoundException {
        System.out.println("Adding metric value: " + metricValue.getPlayerId() + " " + metricValue.getMetricName() + " " + metricValue.getValue());
        return playerService.addMetricValue(metricValue);
    }

    @PostMapping("/player/real-time-extra-details-last-24-hours")
    @PreAuthorize("@securityService.hasAccessToUser(#playerId)")
    public RealTimeExtraDetailsPlayer api_real_time_extra_details_last_24_hours(@RequestParam Long playerId) throws ResourceNotFoundException {
        return playerService.getRealTimeExtraDetailsLast24Hours(playerId);
    }

}
