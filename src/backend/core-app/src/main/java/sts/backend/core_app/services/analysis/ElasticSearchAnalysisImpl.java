package sts.backend.core_app.services.analysis;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import sts.backend.core_app.models.SensorsLogEntity;
import sts.backend.core_app.models.EndpointsEntity;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {
    
    private final ElasticsearchOperations elasticsearchOperations;
    private final TimeSeriesQueries timeSeriesQueries;

    private final IndexCoordinates sensorsIndex;
    private final IndexCoordinates endpointsIndex;

    public ElasticSearchAnalysisImpl(ElasticsearchOperations elasticsearchOperations, TimeSeriesQueries timeSeriesQueries) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.timeSeriesQueries = timeSeriesQueries;
        this.sensorsIndex = elasticsearchOperations.getIndexCoordinatesFor(SensorsLogEntity.class);
        this.endpointsIndex = elasticsearchOperations.getIndexCoordinatesFor(EndpointsEntity.class);
    }

    public List<SensorsLogEntity> getLogs() {
        Criteria criteria = new Criteria(); // 
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHis = elasticsearchOperations.search(query, SensorsLogEntity.class, sensorsIndex);
        return searchHis.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        // TODO: remove this!
        SensorsLogEntity log = new SensorsLogEntity();
        log.setId(UUID.randomUUID().toString());
        log.setTeamId(1L);
        log.setMessage("Produced to topic: " + metricValue.getMetricName() + " with value: " + metricValue.getValue());
        log.setTimestamp(System.currentTimeMillis());
        try {
            System.out.println("Saving log to Elasticsearch");
            elasticsearchOperations.save(log, sensorsIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Produced to topic: " + metricValue.getMetricName() + " with value: " + metricValue.getValue());
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }
}
