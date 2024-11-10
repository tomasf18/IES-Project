package sts.backend.core_app.persistence.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface TimeSeriesQueries {

    SensorTimeSeriesData addMetricValue(Long playerId, String metricName, Double value) throws ResourceNotFoundException;

    RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);

    List<ValueTimeSeriesView> getHistoricalData(Long playerId, String metric, LocalDateTime startTime,
            LocalDateTime endTime);

    Double getAverageValue(List<ValueTimeSeriesView> metricData);
}
