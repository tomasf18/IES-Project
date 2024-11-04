package sts.backend.core_app.persistence.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("""
            SELECT  s.id AS sessionId, 
                    s.startTime as startTime,
                    (
                        SELECT COUNT(ps)
                        FROM playerSessions ps
                        WHERE ps.session = s
                    ) As numParticipants,
                    CASE
                        WHEN s.endTime IS NULL THEN 'Open'
                        ELSE 'Closed'
                    END AS state,
                    (s.opponentTeam IS NOT NULL) AS isMatch
            FROM sessions s
            JOIN s.trainer t
            JOIN t.team team
            WHERE t.team = :team
            """)
    Optional<Set<SessionInfoView>> findSessionInfoByTeam(@Param("team") Team team);
}
    