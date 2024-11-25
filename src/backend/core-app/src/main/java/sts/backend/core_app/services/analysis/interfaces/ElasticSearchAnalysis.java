package sts.backend.core_app.services.analysis.interfaces;

import java.util.List;

import sts.backend.core_app.models.LogEntity;

public interface ElasticSearchAnalysis {
    List<LogEntity> getLogs();
}
