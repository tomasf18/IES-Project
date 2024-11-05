package sts.backend.core_app.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import sts.backend.core_app.models.SensorTimeSeriesData; // Add this import statement

// Used for directly access to the TimescaleDB database
@Service
public class SensorDataService {

    // Should put the "DataSource" part in any service or repository class where I need to use the timescaleDataSource
    // This allows Spring Boot project to leverage the TimescaleDB connection for specific queries or operations
    private final DataSource timescaleDataSource;

    public SensorDataService(@Qualifier("timescaleDataSource") DataSource timescaleDataSource) {
        this.timescaleDataSource = timescaleDataSource;
    }

    public void saveSensorData(SensorTimeSeriesData sensorData) {
        try (Connection connection = timescaleDataSource.getConnection()) {
            // Implement logic for saving sensor data to TimescaleDB using JDBC or a JPA repository.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
