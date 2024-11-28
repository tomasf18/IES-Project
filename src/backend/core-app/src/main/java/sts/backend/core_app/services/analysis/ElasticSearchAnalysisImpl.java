package sts.backend.core_app.services.analysis;

import java.util.List;
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

import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {
    
    private final ElasticsearchOperations elasticsearchOperations;

    private final IndexCoordinates sensorsIndex;
    private final IndexCoordinates endpointsIndex;

    public ElasticSearchAnalysisImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.sensorsIndex = elasticsearchOperations.getIndexCoordinatesFor(SensorsLogEntity.class);
        this.endpointsIndex = elasticsearchOperations.getIndexCoordinatesFor(EndpointsEntity.class);
    }

    public List<SensorsLogEntity> getLogs() {
        // TODO: remove this method (only to demonstrate how to read data from Elasticsearch)
        Criteria criteria = new Criteria(); // 
        Query query = new CriteriaQuery(criteria);
        SearchHits<SensorsLogEntity> searchHis = elasticsearchOperations.search(query, SensorsLogEntity.class, sensorsIndex);
        return searchHis.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

    public void addSensorsLog(SensorsLogEntity sensorsLogEntity) {
        elasticsearchOperations.save(sensorsLogEntity, sensorsIndex);
    }
}
