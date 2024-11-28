package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface ElasticSearchAnalysis {
    List<SensorsLogEntity> getLogs();
    SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException;
}
