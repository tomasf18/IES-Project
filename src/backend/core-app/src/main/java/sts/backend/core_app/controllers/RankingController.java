package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.services.business.RankingService;

@RestController
@RequestMapping("/api/v1")
public class RankingController {
    
    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/ranking/sleep")
    public Long api_get_sleep_ranking(@RequestParam Long playerId, @RequestParam Long timeOption) {
        return rankingService.getSleepRanking(playerId, timeOption); // TODO: implement
    }

    @GetMapping("/ranking/recovery-strain")
    public Long api_get_recovery_strain_ranking(@RequestParam Long playerId, @RequestParam Long timeOption) {
        return rankingService.getRecoveryStrainRanking(playerId, timeOption); // TODO: implement
    }

}
