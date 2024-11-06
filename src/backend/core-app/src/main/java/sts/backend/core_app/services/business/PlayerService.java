package sts.backend.core_app.services.business;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.SessionsAllDayOfYear;
import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;

@Service
public class PlayerService {

    private final BasicDataAnalysis basicDataAnalysis;
    private final RealTimeAnalysis realTimeAnalysis;

    public PlayerService(BasicDataAnalysis basicDataAnalysis, RealTimeAnalysis realTimeAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
        this.realTimeAnalysis = realTimeAnalysis;
    }

    public SessionsAllDayOfYear getPlayerSessionsAllDaysOfYear(Long playerId, Long year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerFatigueAllDaysOfYear'");
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return realTimeAnalysis.addMetricValue(metricValue);
    }

    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId) {
        return realTimeAnalysis.getRealTimeExtraDetailsLast24Hours(playerId);
    }
    
}