package sts.backend.core_app.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sts.backend.core_app.models.Team;

import org.springframework.stereotype.Repository;

import sts.backend.core_app.dto.TeamsInfoResponse;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("""
            SELECT new sts.backend.core_app.dto.TeamsInfoResponse(
                t.teamId, 
                t.name, 
                COUNT(DISTINCT p) + COUNT(DISTINCT tr) + COUNT(DISTINCT td)
            )
            FROM teams t
            LEFT JOIN players p ON t.teamId = p.team.teamId
            LEFT JOIN trainers tr ON t.teamId = tr.team.teamId
            LEFT JOIN teamDirectors td ON t.teamId = td.team.teamId
            GROUP BY t.teamId, t.name
            """)
    List<TeamsInfoResponse> findAllTeamsInfo();
}
    