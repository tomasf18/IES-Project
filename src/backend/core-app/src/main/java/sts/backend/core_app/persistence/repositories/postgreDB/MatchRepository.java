package sts.backend.core_app.persistence.repositories.postgreDB;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.Match;

import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
    