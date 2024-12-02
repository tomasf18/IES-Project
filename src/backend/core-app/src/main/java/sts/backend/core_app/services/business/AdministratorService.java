package sts.backend.core_app.services.business;

import org.springframework.stereotype.Service;

import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.dto.admin.SensorsLast5Days;

import java.util.List;
import java.util.Map;

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

    // get all sensors logs in the last 5 days
    public List<SensorsLast5Days> getSensorsLast5Days() {
        return elasticSearchAnalysis.getSensorsLast5Days();
    }

    // get all sensors logs by hour in the day
    public Map<String, Integer> getSensorsDay(String date) {
        return elasticSearchAnalysis.getSensorsDay(date);
    }


// get daily-comparison for all last 5 days

// get access of last 5 days for each day
// including total of day, and per hour

}
