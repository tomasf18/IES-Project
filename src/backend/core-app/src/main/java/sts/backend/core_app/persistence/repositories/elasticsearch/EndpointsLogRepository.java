package sts.backend.core_app.persistence.repositories.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import sts.backend.core_app.models.EndpointsEntity;

public interface EndpointsLogRepository extends ElasticsearchRepository<EndpointsEntity, String> {
    List<EndpointsEntity> findAll();
}
