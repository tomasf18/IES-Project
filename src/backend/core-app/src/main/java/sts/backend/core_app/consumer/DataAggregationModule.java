package sts.backend.core_app.consumer;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.dto.session.Record;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;

@Service
public class DataAggregationModule {
    
    private final RealTimeAnalysis realTimeAnalysis;
    private final RelationalQueries relationalQueries;
    private final DataTransformationModule dataTransformationModule;
    private final WebSocketController webSocketController;
    
    public DataAggregationModule(DataTransformationModule dataTransformationModule, RealTimeAnalysis realTimeAnalysis, WebSocketController webSocketController, RelationalQueries relationalQueries) {
        this.dataTransformationModule = dataTransformationModule;
        this.relationalQueries = relationalQueries;
        this.realTimeAnalysis = realTimeAnalysis;
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
            playerId = realTimeAnalysis.getPlayerIdBySensorId(sensorId);

            dataTransformationModule.transformAndSendMessage(playerId, heartRate, respiratoryRate, bodyTemperature);

            Set<SessionInfoView> session = relationalQueries.getSessionByPlayerId(playerId);
            
            // Get last session id added to the set
            Long sessionId = session.stream().findFirst().get().getSessionId();
            RealTimeInfoResponse realTimeInfo = realTimeAnalysis.getRealTimeInfo(sessionId);
            webSocketController.sendRealTimeInfo(realTimeInfo);
        }    
    }
}