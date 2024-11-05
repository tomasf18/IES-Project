package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.player.FatigueResponse;
import sts.backend.core_app.dto.player.OverviewStressResponse;
import sts.backend.core_app.dto.player.RecoveryStrainResponse;
import sts.backend.core_app.dto.player.SleepResponse;
import sts.backend.core_app.services.business.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/overview-stress")
    public OverviewStressResponse api_get_overview_stress(@RequestParam Long playerId, @RequestParam String timeOption) {
        return playerService.getOverviewStress(playerId, timeOption); // TODO: implement
    }

    @GetMapping("/player/sleep")
    public SleepResponse api_get_sleep(@RequestParam Long playerId, @RequestParam String timeOption) {
        return playerService.getSleep(playerId, timeOption); // TODO: implement
    }

    @GetMapping("/player/recovery-strain")
    public RecoveryStrainResponse api_get_recovery_strain(@RequestParam Long playerId, @RequestParam String timeOption) {
        return playerService.getRecoveryStrain(playerId, timeOption); // TODO: implement
    }

    @GetMapping("/player/player-fatigue/all-days-of-year")
    public FatigueResponse api_get_player_fatigue_all_days_of_year(@RequestParam Long playerId, @RequestParam Long year) {
        return playerService.getPlayerFatigueAllDaysOfYear(playerId, year); // TODO: implement
    }

    @PostMapping("/player/add-metric-value")
    public void api_add_metric_value(@RequestParam Long playerId, @RequestParam String metricName, @RequestParam String value) {
        playerService.addMetricValue(playerId, metricName, value); // TODO: implement
    }

}
