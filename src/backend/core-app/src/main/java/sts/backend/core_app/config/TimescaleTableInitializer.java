package sts.backend.core_app.config;

import java.util.Set;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;

@Configuration
public class TimescaleTableInitializer {
    private final EntityManager entityManager;

    public TimescaleTableInitializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private void createHypertable(String tableName, String timeColumn) {
        entityManager.createNativeQuery(String.format(
            "SELECT create_hypertable('%s','%s', if_not_exists => TRUE);", 
            tableName, 
            timeColumn
        )).getResultList();
    }

    @PostConstruct
    public void init() {
        // get all entities
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

        // for each entity
        for (EntityType<?> entity : entities) {
            // get entity class
            Class<?> javaType = entity.getJavaType();

            // check of TimescaleTable annotation
            if (javaType.isAnnotationPresent(TimescaleTable.class)) {
                // get metadata from annotation
                TimescaleTable annotation = javaType.getAnnotation(TimescaleTable.class);
                String tableName = annotation.tableName();
                String timeColumnName = annotation.timeColumnName();

                // create hypertable
                createHypertable(tableName, timeColumnName);
            }
        }
    }

}
