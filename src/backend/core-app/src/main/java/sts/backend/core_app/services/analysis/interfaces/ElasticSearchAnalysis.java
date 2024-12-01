package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.dto.admin.SensorsTeamWeek;
import sts.backend.core_app.dto.admin.SensorsLast5Days;

public interface ElasticSearchAnalysis {
    List<SensorsLogEntity> getLogs();
    List<SensorsTeamWeek> getSensors();
    List<SensorsLast5Days> getSensorsLast5Days();
    void addSensorsLog(SensorsLogEntity sensorsLogEntity);
}
