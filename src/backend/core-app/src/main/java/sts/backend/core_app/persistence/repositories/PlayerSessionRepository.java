package sts.backend.core_app.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.PlayerSession;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerSessionRepository extends JpaRepository<PlayerSession, Long> {

}
    