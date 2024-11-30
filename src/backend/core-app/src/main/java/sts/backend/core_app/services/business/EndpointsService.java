package sts.backend.core_app.services.business;

import org.springframework.stereotype.Service;

import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class EndpointsService {
    
    private final ElasticSearchAnalysis elasticSearchAnalysis;

    public EndpointsService(ElasticSearchAnalysis elasticSearchAnalysis) {
        this.elasticSearchAnalysis = elasticSearchAnalysis;
    }

}
