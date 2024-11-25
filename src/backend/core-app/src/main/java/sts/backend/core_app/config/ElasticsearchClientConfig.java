package sts.backend.core_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@EnableElasticsearchRepositories(basePackages = "sts.backend.core_app.persistence.repositories.elasticsearch")
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {
    
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()           
            .connectedTo("localhost:9200")
            .build();
    }
}
