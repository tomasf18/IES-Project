package sts.backend.core_app.services.business;

import org.springframework.stereotype.Service;

import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;

import java.util.List;

@Service
public class AdministratorService {

    private final ElasticSearchAnalysis elasticSearchAnalysis;

    public AdministratorService(ElasticSearchAnalysis elasticSearchAnalysis) {
        this.elasticSearchAnalysis = elasticSearchAnalysis;
    }

    // get all logs (TEST PURPOSES)
    public List<SensorsLogEntity> getLogs() {
        return elasticSearchAnalysis.getLogs();
    }

    // get all sensors logs by team in the week
    public List<SensorsTeamWeek> getSensors() {
        return elasticSearchAnalysis.getSensors();
    }


// get daily-comparison for all last 5 days

// get access of last 5 days for each day
// including total of day, and per hour

}
