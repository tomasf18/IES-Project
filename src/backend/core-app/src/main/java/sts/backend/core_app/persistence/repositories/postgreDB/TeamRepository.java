package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sts.backend.core_app.dto.team.SensorPlayerView;
import sts.backend.core_app.dto.team.TeamsInfoView;
import sts.backend.core_app.models.Team;

import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("""
            SELECT  t.teamId AS teamId, 
                    t.name AS name, 
                    COUNT(DISTINCT p) + COUNT(DISTINCT tr) + COUNT(DISTINCT td) AS numberOfMembers
            FROM teams t
            LEFT JOIN players p ON t.teamId = p.team.teamId
            LEFT JOIN trainers tr ON t.teamId = tr.team.teamId
            LEFT JOIN teamDirectors td ON t.teamId = td.team.teamId
            GROUP BY t.teamId, t.name
            """)
    Optional<Set<TeamsInfoView>> findAllTeamsInfo();

    @Query  ("""
            SELECT s.sensorId as sensorId, p.name as name
            FROM sensors s
            LEFT JOIN playerSensors ps ON ps.sensor.sensorId = s.sensorId
            LEFT JOIN players p ON ps.player.userId = p.userId
            WHERE s.team.teamId = :teamId
            """)
    Optional<Set<SensorPlayerView>> findSensorsWithPlayersByTeamId(Long teamId);
}
    