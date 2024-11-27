package sts.backend.core_app.persistence.repositories.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sts.backend.core_app.models.LogEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ElasticsearchRepository<LogEntity, Long> {
}