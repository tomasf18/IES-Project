package sts.backend.core_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "sts.backend.core_app.persistence.repositories.elasticsearch")
public class ElasticsearchConfig {
    // Additional Elasticsearch-specific configurations (if needed)
}