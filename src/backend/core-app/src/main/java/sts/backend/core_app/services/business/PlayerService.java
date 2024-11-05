package sts.backend.core_app.services.business;

import sts.backend.core_app.dto.player.RecoveryStrainResponse;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.FatigueResponse;
import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.OverviewStressResponse;
import sts.backend.core_app.dto.player.SleepResponse;
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

    public OverviewStressResponse getOverviewStress(Long playerId, String timeOption) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOverviewStress'");
    }

    public SleepResponse getSleep(Long playerId, String timeOption) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSleep'");
    }

    public RecoveryStrainResponse getRecoveryStrain(Long playerId, String timeOption) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecoveryStrain'");
    }

    public FatigueResponse getPlayerFatigueAllDaysOfYear(Long playerId, Long year) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerFatigueAllDaysOfYear'");
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return realTimeAnalysis.addMetricValue(metricValue);
    }
    
}