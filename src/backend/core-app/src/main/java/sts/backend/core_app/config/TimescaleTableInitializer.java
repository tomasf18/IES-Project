package sts.backend.core_app.config;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import javax.sql.DataSource;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;

@Configuration
public class TimescaleTableInitializer implements ApplicationRunner {

    private final EntityManager entityManager;

    public TimescaleTableInitializer(@Lazy EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void createHypertable(String tableName, String timeColumn) {
        entityManager.createNativeQuery(String.format(
            "SELECT create_hypertable('%s','%s', if_not_exists => TRUE);", 
            tableName, 
            timeColumn
        )).getResultList();
    }

    @Bean(name = "timescaleDataSource")
    public DataSource dataSource(
        @Value("${spring.datasource.url}") String url,
        @Value("${spring.datasource.username}") String username,
        @Value("${spring.datasource.password}") String password
    ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Get all entities
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // For each entity
        for (EntityType<?> entity : entities) {
            // Get entity class
            Class<?> javaType = entity.getJavaType();

            // Check for TimescaleTable annotation
            if (javaType.isAnnotationPresent(TimescaleTable.class)) {
                // Get metadata from annotation
                TimescaleTable annotation = javaType.getAnnotation(TimescaleTable.class);
                String tableName = annotation.tableName();
                String timeColumnName = annotation.timeColumnName();

                // Create hypertable
                createHypertable(tableName, timeColumnName);
            }
        }
    }
}
