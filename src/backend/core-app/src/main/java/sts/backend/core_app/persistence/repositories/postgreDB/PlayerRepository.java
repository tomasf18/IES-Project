package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Find players without PlayerSensor linked to them for a specific team
    List<Player> findByTeamTeamIdAndPlayerSensorIsNull(Long teamId);
    
    List<Player> findPlayersByTeamTeamId(Long teamId);

    List<Player> findByTeamTeamIdAndPlayerSensorIsNotNull(Long teamId);
}
