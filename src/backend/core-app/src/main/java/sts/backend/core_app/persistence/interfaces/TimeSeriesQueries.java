package sts.backend.core_app.persistence.interfaces;

import java.util.List;
import java.time.LocalDateTime;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.session.SessionLastMetricValues;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;

public interface TimeSeriesQueries {

    SensorTimeSeriesData addMetricValue(Long playerId, String metricName, Double value) throws ResourceNotFoundException;

    RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);

    List<ValueTimeSeriesView> getHeartRateData(Long playerId, LocalDateTime initialTimestamp);

    SessionLastMetricValues getLastMetricValuesByPlayerId(Long playerId) throws ResourceNotFoundException;

    List<ValueTimeSeriesView> getHistoricalData(Long playerId, String metric, LocalDateTime startTime,
            LocalDateTime endTime);

    Double getAverageValue(List<ValueTimeSeriesView> metricData);
}
