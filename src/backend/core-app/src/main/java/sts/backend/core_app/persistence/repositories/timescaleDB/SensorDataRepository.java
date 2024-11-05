package sts.backend.core_app.persistence.repositories.timescaleDB;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sts.backend.core_app.models.SensorTimeSeriesData;
import sts.backend.core_app.models.SensorTimeSeriesDataId;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorTimeSeriesData, SensorTimeSeriesDataId> {

}
