package sts.backend.core_app.services.analysis;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.RealTimeAnalysis;

@Service
public class RealTimeAnalysisImpl implements RealTimeAnalysis {
    
    private final TimeSeriesQueries timeSeriesQueries;

    public RealTimeAnalysisImpl(TimeSeriesQueries timeSeriesQueries) {
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }


}