package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sts.backend.core_app.models.PlayerSession;
import sts.backend.core_app.models.PlayerSessionId;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSessionRepository extends JpaRepository<PlayerSession, PlayerSessionId> {

    @Query("SELECT ps.player.userId FROM playerSessions ps WHERE ps.session.sessionId = :sessionId")
    List<Long> findPlayerUserIdsBySessionSessionId(Long sessionId);
}
    