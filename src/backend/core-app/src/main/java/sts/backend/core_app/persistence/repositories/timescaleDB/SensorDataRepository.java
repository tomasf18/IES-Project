package sts.backend.core_app.persistence.repositories.timescaleDB;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import sts.backend.core_app.models.SensorData;
import sts.backend.core_app.models.SensorDataId;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, SensorDataId> {

}
