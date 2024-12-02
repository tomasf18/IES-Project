package sts.backend.core_app.consumer.interfaces;

import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface DataTransformationModule {
    public void transformAndSendMessage(Long playerId, Double heartRate, Double respiratoryRate, Double bodyTemperature) throws ResourceNotFoundException;
}
