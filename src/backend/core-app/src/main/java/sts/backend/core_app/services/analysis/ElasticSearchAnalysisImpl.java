package sts.backend.core_app.services.analysis;

import java.util.List;

import org.springframework.stereotype.Service;

import sts.backend.core_app.models.LogEntity;
import sts.backend.core_app.persistence.repositories.elasticsearch.LogRepository;
import sts.backend.core_app.services.analysis.interfaces.ElasticSearchAnalysis;

@Service
public class ElasticSearchAnalysisImpl implements ElasticSearchAnalysis {
    
    private LogRepository logRepository;

    public ElasticSearchAnalysisImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<LogEntity> getLogs() {
        return logRepository.findAll();
    }
}
