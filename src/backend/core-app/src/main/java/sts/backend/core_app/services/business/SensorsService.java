package sts.backend.core_app.services.business;

import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
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
        SensorsLogEntity log = new SensorsLogEntity();
        log.setId(UUID.randomUUID().toString());
        log.setTeamId(relationalQueries.getTeamIdByPlayerId(playerId));
        log.setTimestamp( System.currentTimeMillis());
        log.setMessage(message);
        elasticSearchAnalysis.addSensorsLog(log);
    }

}
