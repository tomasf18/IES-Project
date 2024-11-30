package sts.backend.core_app.services.business;

import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class SensorsService {
    
    private final ElasticSearchAnalysis elasticSearchAnalysis;
    private final RelationalQueries relationalQueries;

    public SensorsService(ElasticSearchAnalysis elasticSearchAnalysis, RelationalQueries relationalQueries) {
        this.elasticSearchAnalysis = elasticSearchAnalysis;
        this.relationalQueries = relationalQueries;
    }

    public void addSensorsLog(Long playerId, String message) throws ResourceNotFoundException {
        Player player = relationalQueries.getPlayerById(playerId);
        SensorsLogEntity log = new SensorsLogEntity();
        log.setId(UUID.randomUUID().toString());
        log.setTeamId(relationalQueries.getTeamIdByPlayers(player));
        log.setTimestamp( System.currentTimeMillis());
        log.setMessage(message);
        elasticSearchAnalysis.addSensorsLog(log);
    }

}
