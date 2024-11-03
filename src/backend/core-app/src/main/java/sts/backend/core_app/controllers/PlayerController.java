package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.IdLong;
import sts.backend.core_app.dto.player.OverviewStressResponse;
import sts.backend.core_app.dto.player.PlayerIdWithTimeOption;
import sts.backend.core_app.dto.player.RecoveryStrainResponse;
import sts.backend.core_app.dto.player.SleepResponse;
import sts.backend.core_app.services.business.PlayerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/player/overview-stress")
    public OverviewStressResponse api_get_overview_stress(@RequestBody PlayerIdWithTimeOption playerIdWithTimeOption) {
        return playerService.getOverviewStress(playerIdWithTimeOption); // TODO: implement
    }

    @GetMapping("/player/sleep")
    public SleepResponse api_get_sleep(@RequestBody PlayerIdWithTimeOption playerIdWithTimeOption) {
        return playerService.getSleep(playerIdWithTimeOption); // TODO: implement
    }

    @GetMapping("/player/recovery-strain")
    public RecoveryStrainResponse api_get_recovery_strain(@RequestBody PlayerIdWithTimeOption playerIdWithTimeOption) {
        return playerService.getRecoveryStrain(playerIdWithTimeOption); // TODO: implement
    }

}
