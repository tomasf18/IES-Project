package sts.backend.core_app.config;

import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
    basePackages = "sts.backend.core_app.persistence.repositories.postgreDB",
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        sts.backend.core_app.persistence.repositories.elasticsearch.LogRepository.class,
        sts.backend.core_app.persistence.repositories.timescaleDB.SensorTimeSeriesDataRepository.class
    })
)
public class JpaConfig {
    // Additional JPA-specific configurations (if needed)
}