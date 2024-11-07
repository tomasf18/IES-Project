package sts.backend.core_app.services.analysis;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.dto.team.PlayersAvailableRealTimeInfo;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.persistence.interfaces.RelationalQueries;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;

@Service
public class RealTimeAnalysisImpl implements RealTimeAnalysis {
    
    private final TimeSeriesQueries timeSeriesQueries;
    private final RelationalQueries relationalQueries;

    public RealTimeAnalysisImpl(TimeSeriesQueries timeSeriesQueries, RelationalQueries relationalQueries) {
        this.timeSeriesQueries = timeSeriesQueries;
        this.relationalQueries = relationalQueries;
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }

    @Override
    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        return timeSeriesQueries.getRealTimeExtraDetailsLast24Hours(playerId);
    }

    public Set<PlayersAvailableRealTimeInfo> getPlayersAvailableRealTimeInfo(Long teamId) throws ResourceNotFoundException {
        Set<PlayersAvailableRealTimeInfo> playersAvailableRealTimeInfo = new HashSet<>();
        List<Player> players = relationalQueries.getAvailablePlayersByTeamId(teamId);
        LocalDateTime initialTimestamp = LocalDateTime.now().minusMinutes(5);

        for (Player player : players) {
            List<ValueTimeSeriesView> heartRateData = timeSeriesQueries.getHeartRateData(player.getUserId(), initialTimestamp);
            Double currentHeartRate = heartRateData.get(heartRateData.size() - 1).getValue();

            playersAvailableRealTimeInfo.add(new PlayersAvailableRealTimeInfo(player.getName(), player.getProfilePictureUrl(), heartRateData, currentHeartRate));
        }
        
        return playersAvailableRealTimeInfo;
    }

}