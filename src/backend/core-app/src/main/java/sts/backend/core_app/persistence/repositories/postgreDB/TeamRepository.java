package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sts.backend.core_app.dto.team.TeamMembersResponse;
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

    @Query("""
    SELECT NEW sts.backend.core_app.dto.team.TeamMembersResponse(
        rc.id AS id,
        rc.name AS name,
        rc.profilePictureUrl AS profilePictureUrl,
        rc.userTypeId AS userTypeId,
        rc.code AS registrationCode
    )
    FROM registrationCodes rc
    WHERE rc.team.teamId = :teamId AND rc.userTypeId IN :userTypeId
    """)
    List<TeamMembersResponse> findPendingUsersByTypeId(@Param("teamId") Long teamId, @Param("userTypeId") Set<Long> userTypeId);

    @Query("""
        SELECT s.sensorId as sensorId, p.name as name, p.id as playerId
        FROM sensors s
        LEFT JOIN playerSensors ps ON ps.sensor.sensorId = s.sensorId
        LEFT JOIN players p ON ps.player.userId = p.userId
        WHERE s.team.teamId = :teamId
        """)
    Optional<Set<SensorPlayerView>> findSensorsWithPlayersByTeamId(Long teamId);
}
    