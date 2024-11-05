package sts.backend.core_app.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sts.backend.core_app.dto.team.TeamMembersResponse;
import sts.backend.core_app.models.Player;

import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findPlayersByTeamTeamId(Long teamId);


}
    