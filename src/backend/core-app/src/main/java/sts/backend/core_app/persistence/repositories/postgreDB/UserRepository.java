package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Trainer> findTrainerByUserId(Long trainerId);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<Player> findPlayerByUserId(Long playerId);

    void deleteByUsername(String username);


}
