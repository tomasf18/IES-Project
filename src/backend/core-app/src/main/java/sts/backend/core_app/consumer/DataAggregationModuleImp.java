package sts.backend.core_app.consumer;

import java.util.UUID;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.consumer.WebSocketControllerImp;
import sts.backend.core_app.consumer.interfaces.DataAggregationModule;
import sts.backend.core_app.dto.session.Record;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;
import sts.backend.core_app.services.business.SensorsService;

@Service
public class DataAggregationModuleImp implements DataAggregationModule {
    
    private final SensorsService sensorsService;
    private final RelationalQueries relationalQueries;
    private final DataTransformationModuleImp dataTransformationModule;
    private final WebSocketControllerImp webSocketController;
    
    public DataAggregationModuleImp(SensorsService sensorsService, RelationalQueries relationalQueries, DataTransformationModuleImp dataTransformationModule, WebSocketControllerImp webSocketController) {
        this.sensorsService = sensorsService;
        this.relationalQueries = relationalQueries;
        this.dataTransformationModule = dataTransformationModule;
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

            // notify elasticSearch
            sensorsService.addSensorsLog(playerId, "Received: heart_rate = " + heartRate);
            sensorsService.addSensorsLog(playerId, "Received: respiratory_rate = " + respiratoryRate);
            sensorsService.addSensorsLog(playerId, "Received: body_temperature = " + bodyTemperature);

            // Send the message to the WebSocket TODO...
        }    
    }

    
}
