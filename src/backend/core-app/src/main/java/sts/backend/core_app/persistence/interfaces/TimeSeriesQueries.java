package sts.backend.core_app.persistence.interfaces;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface TimeSeriesQueries {

    SensorTimeSeriesData addMetricValue(Long playerId, String metricName, Double value) throws ResourceNotFoundException;

    RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);
}
