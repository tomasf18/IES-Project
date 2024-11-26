package sts.backend.core_app.services.analysis;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import sts.backend.core_app.models.LogEntity;

import sts.backend.core_app.dto.player.MetricValue;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.persistence.interfaces.TimeSeriesQueries;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {
    
    private ElasticsearchOperations elasticsearchOperations;
    private TimeSeriesQueries timeSeriesQueries;

    public ElasticSearchAnalysisImpl(ElasticsearchOperations elasticsearchOperations, TimeSeriesQueries timeSeriesQueries) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.timeSeriesQueries = timeSeriesQueries;
    }

    public List<LogEntity> getLogs() {
        elasticsearchOperations.indexOps(LogEntity.class).create(); // if exists, it will ignore
        Criteria criteria = new Criteria("type").is("kafka");
        Query query = new CriteriaQuery(criteria);
        SearchHits<LogEntity> searchHis = elasticsearchOperations.search(query, LogEntity.class);
        return searchHis.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public SensorTimeSeriesData addMetricValue(MetricValue metricValue) throws ResourceNotFoundException {
        // TODO: remove this!
        LogEntity log = new LogEntity();
        log.setType("kafka");
        log.setMessage("Produced to topic: " + metricValue.getMetricName() + " with value: " + metricValue.getValue());
        log.setTimestamp(System.currentTimeMillis());
        try {
            elasticsearchOperations.indexOps(LogEntity.class).create(); // if exists, it will ignore
            elasticsearchOperations.save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Produced to topic: " + metricValue.getMetricName() + " with value: " + metricValue.getValue());
        return timeSeriesQueries.addMetricValue(metricValue.getPlayerId(), metricValue.getMetricName(), metricValue.getValue());
    }
}
