package sts.backend.core_app.services.analysis.interfaces;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.dto.player.RealTimeExtraDetailsPlayer;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;

public interface RealTimeAnalysis {
    
    // --- Create methods ---
    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException;

    public RealTimeExtraDetailsPlayer getRealTimeExtraDetailsLast24Hours(Long playerId);

}
