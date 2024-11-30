package sts.backend.core_app.persistence.repositories.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sts.backend.core_app.models.SensorsLogEntity;


public interface SensorsLogRepository extends ElasticsearchRepository<SensorsLogEntity, String> {
    List<SensorsLogEntity> findAll();
}