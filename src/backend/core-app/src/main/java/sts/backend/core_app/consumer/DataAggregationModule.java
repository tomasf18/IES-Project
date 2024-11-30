package sts.backend.core_app.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.dto.session.Message;
import sts.backend.core_app.dto.session.Record;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;

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

        List<Long> sessionIDs = new ArrayList<>();
        List<Long> teamIds = new ArrayList<>();

        for (Record record : records) {
            sensorId = record.getSensorId();
            heartRate = record.getHeartRate();
            respiratoryRate = record.getRespiratoryRate();
            bodyTemperature = record.getBodyTemperature();

            // Implement logic for saving sensor data to TimescaleDB using JDBC or a JPA repository.
            playerId = realTimeAnalysis.getPlayerIdBySensorId(sensorId);
            dataTransformationModule.transformAndSendMessage(playerId, heartRate, respiratoryRate, bodyTemperature);
            
            Player player = relationalQueries.getPlayerById(playerId);
            Team team = player.getTeam();
            Long teamId = team.getTeamId();
            
            Set<SessionInfoView> sessionsInfo = relationalQueries.getSessionsInfoByPlayerId(playerId);
            System.out.println("\n\nSESSION INFO: " + sessionsInfo + "\n\n");

            Long sessionId = sessionsInfo.stream().findFirst().get().getSessionId();


            // I only want to send this kind of data if there is a session
            if (sessionId != null) {
                RealTimeExtraDetailsResponse realTimeExtraDetailsResponse = realTimeAnalysis.getRealTimeExtraDetails(sessionId, playerId);
                System.out.println("Real time extra details response: " + realTimeExtraDetailsResponse);
                webSocketController.sendPlayersRealTimeExtraDetails(playerId, realTimeExtraDetailsResponse);
                
                sessionIDs.add(sessionId);
            }
            
            teamIds.add(teamId);    
        }    

        for (Long sessionId : sessionIDs) {
            RealTimeInfoResponse realTimeInfo = realTimeAnalysis.getRealTimeInfo(sessionId);
            webSocketController.sendRealTimeInfo(sessionId, realTimeInfo);
        }

        for (Long teamId : teamIds) {
            List<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo = realTimeAnalysis.getPlayersAvailableRealTimeInfo(teamId);
            webSocketController.sendPlayersAvailableRealTimeInfo(teamId, playersAvailableRealTimeInfo);
        }
    }
}