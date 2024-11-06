package sts.backend.core_app.persistence;

import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.dto.session.HistoricalInfoResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.SensorTimeSeriesDataId;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.persistence.repositories.timescaleDB.SensorTimeSeriesDataRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TimeSeriesQueriesImpl implements TimeSeriesQueries {

    private final SensorTimeSeriesDataRepository sensorTimeSeriesDataRepository; 
    private final RelationalQueries relationalQueries;

    public TimeSeriesQueriesImpl(SensorTimeSeriesDataRepository sensorTimeSeriesDataRepository, RelationalQueries relationalQueries) {
        this.sensorTimeSeriesDataRepository = sensorTimeSeriesDataRepository;
        this.relationalQueries = relationalQueries;
    }

    public SensorTimeSeriesData addMetricValue(Long playerId, String metricName, Double value) throws ResourceNotFoundException {
        SensorTimeSeriesDataId sensorTimeSeriesDataId =  new SensorTimeSeriesDataId(LocalDateTime.now(), playerId, metricName);
        SensorTimeSeriesData sensorTimeSeriesData = new SensorTimeSeriesData(
            sensorTimeSeriesDataId,
            relationalQueries.getPlayerById(playerId),
            value
        );
        return sensorTimeSeriesDataRepository.save(sensorTimeSeriesData);
    }

    @Override
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        LocalDateTime initialTimestamp = LocalDateTime.now().minusHours(24);

        List<ValueTimeSeriesView> heartRateData = sensorTimeSeriesDataRepository.findByPlayerUserIdAndIdMetricAndIdTimestampAfter(playerId, "heart_rate", initialTimestamp);
        List<ValueTimeSeriesView> bodyTemperatureData = sensorTimeSeriesDataRepository.findByPlayerUserIdAndIdMetricAndIdTimestampAfter(playerId, "body_temperature", initialTimestamp);
        List<ValueTimeSeriesView> respiratoryRateData = sensorTimeSeriesDataRepository.findByPlayerUserIdAndIdMetricAndIdTimestampAfter(playerId, "respiratory_rate", initialTimestamp);

        RealTimeExtraDetailsPlayer details = new RealTimeExtraDetailsPlayer();
        details.setHeartRateData(heartRateData);
        details.setBodyTemperatureData(bodyTemperatureData);
        details.setRespiratoryRateData(respiratoryRateData);

        return details;
    }

    @Override
    public List<ValueTimeSeriesView> getHistoricalData(Long playerId, String metric, LocalDateTime startTime,
            LocalDateTime endTime) {
        return sensorTimeSeriesDataRepository.findByPlayerUserIdAndIdMetricAndIdTimestampBetween(playerId, metric, startTime, endTime);
    }

}
