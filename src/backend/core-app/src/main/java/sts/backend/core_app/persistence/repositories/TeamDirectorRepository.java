package sts.backend.core_app.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.TeamDirector;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamDirectorRepository extends JpaRepository<TeamDirector, Long> {

}
    