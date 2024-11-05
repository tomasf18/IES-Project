package sts.backend.core_app.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sts.backend.core_app.models.Player;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findPlayersByTeamTeamId(Long teamId);


}
    