package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.Trainer;

import org.springframework.stereotype.Repository;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    List<Trainer> findTrainersByTeamTeamId(Long teamId);
    
    List<Trainer> findByTeamTeamIdAndIsCoachTrue(Long teamId);

    List<Trainer> findByTeamTeamIdAndIsCoachFalse(Long teamId);

}
    