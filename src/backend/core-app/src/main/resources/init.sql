-- Enable TimescaleDB extension
CREATE EXTENSION IF NOT EXISTS timescaledb;

-- Create the sensor_data table and convert it to a hypertable
CREATE TABLE IF NOT EXISTS sensor_data (
    timestamp       TIMESTAMPTZ           NOT NULL,
    player_id       LONG                  NOT NULL,
    metric          TEXT                  NOT NULL,
    value           DOUBLE                PRECISION,
    PRIMARY KEY (timestamp, player_id, metric)
);

-- Create the table as a hypertable
SELECT create_hypertable('sensor_data', 'timestamp', if_not_exists => TRUE);
