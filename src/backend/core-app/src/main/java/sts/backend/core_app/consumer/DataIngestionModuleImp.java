package sts.backend.core_app.consumer;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import sts.backend.core_app.consumer.interfaces.DataIngestionModule;
import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.exceptions.ResourceNotFoundException;

@Service
public class DataIngestionModuleImp implements DataIngestionModule {

    private final DataAggregationModuleImp dataAggregationModule;

    public DataIngestionModuleImp(DataAggregationModuleImp dataAggregationModule) {
        this.dataAggregationModule = dataAggregationModule;
    }
    
    // @KafkaListener(topics = "sensor_data", groupId = "consumers_1")
    @KafkaListener(topics = "sensor_data", containerFactory = "messageKafkaListenerContainerFactory")
    public void listen(Message message) throws ResourceNotFoundException {
        dataAggregationModule.processMessage(message);
    }
        
}
