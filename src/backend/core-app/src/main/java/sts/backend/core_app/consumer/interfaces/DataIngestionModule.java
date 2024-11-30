package sts.backend.core_app.consumer.interfaces;

import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

public interface DataIngestionModule {
    public void listen(Message message) throws ResourceNotFoundException;
}
