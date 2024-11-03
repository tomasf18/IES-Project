package sts.backend.core_app.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Trainer> findTrainerByUserId(Long trainerId);

}
