package sts.backend.core_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class TimescaleDBConfig {

    // Read the database connection properties from the application.properties file
    @Value("${timescaledb.datasource.url}")
    private String timescaleDbUrl;

    @Value("${timescaledb.datasource.username}")
    private String timescaleDbUsername;

    @Value("${timescaledb.datasource.password}")
    private String timescaleDbPassword;

    @Bean(name = "timescaleDataSource")
    public DataSource timescaleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(timescaleDbUrl);
        dataSource.setUsername(timescaleDbUsername);
        dataSource.setPassword(timescaleDbPassword);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }
}
