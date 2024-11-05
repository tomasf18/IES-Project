package sts.backend.core_app.persistence;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.SensorTimeSeriesDataId;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.persistence.repositories.timescaleDB.SensorTimeSeriesDataRepository;

import java.time.LocalDateTime;

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
    
}
