package sts.backend.core_app.persistence.repositories.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogRepository extends ElasticsearchRepository<LogEntity, String> {
}