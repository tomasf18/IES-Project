package sts.backend.core_app.consumer;

import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;

@Service
public class DataTransformationModule {

    private final TimeSeriesQueries timeSeriesQueries;

    public DataTransformationModule(TimeSeriesQueries timeSeriesQueries) {
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public void transformAndSendMessage(Long playerId, Double heartRate, Double respiratoryRate, Double bodyTemperature) throws ResourceNotFoundException {
        timeSeriesQueries.addMetricValue(playerId, "heart_rate", heartRate); 
        timeSeriesQueries.addMetricValue(playerId, "respiratory_rate", respiratoryRate);
        timeSeriesQueries.addMetricValue(playerId, "body_temperature", bodyTemperature);
    }
}
