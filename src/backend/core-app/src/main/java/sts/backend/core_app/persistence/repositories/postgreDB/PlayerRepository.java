package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sts.backend.core_app.models.Player;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Find players without PlayerSensor linked to them for a specific team
    @Query("SELECT p FROM players p JOIN p.team t LEFT JOIN p.playerSensor ps WHERE t.id = :teamId AND ps IS NULL")
    List<Player> findPlayersWithoutSensorsByTeamId(@Param("teamId") Long teamId);
    
    List<Player> findPlayersByTeamTeamId(Long teamId);

    Optional<Player> findByUsername(String username);


}
