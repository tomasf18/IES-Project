package sts.backend.core_app.consumer;

import org.springframework.stereotype.Service;

import sts.backend.core_app.consumer.interfaces.DataTransformationModule;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;

@Service
public class DataTransformationModuleImp implements DataTransformationModule {

    private final TimeSeriesQueries timeSeriesQueries;

    public DataTransformationModuleImp(TimeSeriesQueries timeSeriesQueries) {
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public void transformAndSendMessage(Long playerId, Double heartRate, Double respiratoryRate, Double bodyTemperature) throws ResourceNotFoundException {
        timeSeriesQueries.addMetricValue(playerId, "heart_rate", heartRate); 
        timeSeriesQueries.addMetricValue(playerId, "respiratory_rate", respiratoryRate);
        timeSeriesQueries.addMetricValue(playerId, "body_temperature", bodyTemperature);
    }
}
