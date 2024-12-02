package sts.backend.core_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
@EnableElasticsearchRepositories(basePackages = "sts.backend.core_app.persistence.repositories.elasticsearch")
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {
    
    @Value("${ELASTICSEARCH_HOST}")
    private String elasticsearchHost;

    @Value("${ELASTICSEARCH_PORT}")
    private int elasticsearchPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()           
            .connectedTo(elasticsearchHost + ":" + elasticsearchPort)
            .build();
    }
}
