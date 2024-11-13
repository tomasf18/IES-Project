package sts.backend.core_app.persistence.repositories.timescaleDB;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.dto.player.ValueTimeSeriesView;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.SensorTimeSeriesDataId;

import java.util.List;

@Repository
public interface SensorTimeSeriesDataRepository extends JpaRepository<SensorTimeSeriesData, SensorTimeSeriesDataId> {
    List<ValueTimeSeriesView> findByPlayerUserIdAndIdMetricAndIdTimestampAfter(Long playerId, String metricType, LocalDateTime timestamp);
    SensorTimeSeriesData findFirstByPlayerUserIdAndIdMetricOrderByIdTimestampDesc(Long playerId, String metricType);

    List<ValueTimeSeriesView> findByPlayerUserIdAndIdMetricAndIdTimestampBetween(Long playerId, String metric,
            LocalDateTime startTime, LocalDateTime endTime);
}
