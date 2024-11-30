package sts.backend.core_app.services.business;

import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class SensorsService {
    
    private final ElasticSearchAnalysis elasticSearchAnalysis;

    public SensorsService(ElasticSearchAnalysis elasticSearchAnalysis) {
        this.elasticSearchAnalysis = elasticSearchAnalysis;
    }

    public void addSensorsLog(Player player, String message) throws ResourceNotFoundException {
        SensorsLogEntity log = new SensorsLogEntity();
        log.setId(UUID.randomUUID().toString());
        log.setTeamId(player.getTeam().getTeamId());
        log.setTimestamp( System.currentTimeMillis());
        log.setMessage(message);
        elasticSearchAnalysis.addSensorsLog(log);
    }

}
