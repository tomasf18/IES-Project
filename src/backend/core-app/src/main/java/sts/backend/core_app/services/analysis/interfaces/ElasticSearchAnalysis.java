package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;

public interface ElasticSearchAnalysis {
    List<SensorsLogEntity> getLogs();
    List<SensorsTeamWeek> getSensors();
    void addSensorsLog(SensorsLogEntity sensorsLogEntity);
}
