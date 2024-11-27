package sts.backend.core_app.consumer;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.consumer.MessageController;
import sts.backend.core_app.dto.session.Record;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;

@Service
public class DataAggregationModule {
    
    private final RelationalQueries relationalQueries;
    private final DataTransformationModule dataTransformationModule;
    private final MessageController webSocketController;
    
    public DataAggregationModule(DataTransformationModule dataTransformationModule, RelationalQueries relationalQueries, MessageController webSocketController) {
        this.dataTransformationModule = dataTransformationModule;
        this.relationalQueries = relationalQueries;
        this.webSocketController = webSocketController;
    }

    public void processMessage(Message message) throws ResourceNotFoundException {
        Record[] records = message.getRecords();

        Long sensorId;
        Double heartRate, respiratoryRate, bodyTemperature;
        Long playerId;

        for (Record record : records) {
            sensorId = record.getSensorId();
            heartRate = record.getHeartRate();
            respiratoryRate = record.getRespiratoryRate();
            bodyTemperature = record.getBodyTemperature();

            // Implement logic for saving sensor data to TimescaleDB using JDBC or a JPA repository.
            playerId = relationalQueries.getPlayerIdBySensorId(sensorId);

            dataTransformationModule.transformAndSendMessage(playerId, heartRate, respiratoryRate, bodyTemperature);

            // Send the message to the WebSocket TODO...
        }    
    }
}
