package sts.backend.core_app.persistence.repositories.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sts.backend.core_app.models.LogEntity;


public interface LogRepository extends ElasticsearchRepository<LogEntity, Long> {
    List<LogEntity> findAll();
}