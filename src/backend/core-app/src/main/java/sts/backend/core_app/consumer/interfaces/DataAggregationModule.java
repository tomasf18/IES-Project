package sts.backend.core_app.consumer.interfaces;

import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface DataAggregationModule {
    public void processMessage(Message message) throws ResourceNotFoundException;
}
