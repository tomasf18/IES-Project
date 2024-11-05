package sts.backend.core_app.persistence.repositories.postgreDB;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.PlayerSensor;
import sts.backend.core_app.models.PlayerSensorId;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSensorRepository extends JpaRepository<PlayerSensor, PlayerSensorId> {

}
    