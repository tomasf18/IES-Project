package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.LogEntity;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface ElasticSearchAnalysis {
    List<LogEntity> getLogs();
    SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException;
}
