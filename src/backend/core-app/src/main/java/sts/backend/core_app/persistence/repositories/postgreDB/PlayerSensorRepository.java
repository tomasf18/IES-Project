package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.PlayerSensorId;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PlayerSensorRepository extends JpaRepository<PlayerSensor, PlayerSensorId> {

    @Query("SELECT ps.player.id FROM playerSensors ps WHERE ps.sensor.sensorId = :sensorId")
    Optional<Long> findPlayerIdBySensorSensorId(@Param("sensorId") Long sensorId);


}
    