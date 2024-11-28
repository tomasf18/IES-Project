package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.models.SensorsLogEntity;

public interface ElasticSearchAnalysis {
    List<SensorsLogEntity> getLogs();
    void addSensorsLog(SensorsLogEntity sensorsLogEntity);
}
