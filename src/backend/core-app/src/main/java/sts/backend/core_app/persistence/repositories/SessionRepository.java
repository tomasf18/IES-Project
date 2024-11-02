package sts.backend.core_app.persistence.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sts.backend.core_app.models.Session;
import sts.backend.core_app.dto.SessionInfoView;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Set<SessionInfoView> findByTrainer_TeamId(Long teamId);
}
    