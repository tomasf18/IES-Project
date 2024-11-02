package sts.backend.core_app.persistence.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.Session;

import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    Set<SessionsInfoRecord> findByTrainer_TeamId(Long teamId);
}
    