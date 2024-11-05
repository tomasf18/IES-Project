package sts.backend.core_app.persistence.repositories.postgreDB;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sts.backend.core_app.dto.team.TeamDirectorsView;
import sts.backend.core_app.models.TeamDirector;

import org.springframework.stereotype.Repository;

@Repository
public interface TeamDirectorRepository extends JpaRepository<TeamDirector, Long> {
    @Query("""
            SELECT td.userId As teamDirectorId,
                   td.name AS name,
                   true AS isOfficialMember,
                   null AS registrationCode
            FROM teamDirectors td
            WHERE td.team.teamId = :teamId
            """)
    Optional<Set<TeamDirectorsView>> findTeamDirectorsByTeamId(@Param("teamId") Long teamId);
    
    @Query("""
            SELECT null As teamDirectorId,
                   rc.name AS name,
                   false AS isOfficialMember,
                   rc.code AS registrationCode
            FROM registrationCodes rc
            WHERE rc.team.teamId = :teamId AND rc.userTypeId = 2
            """)
    Optional<Set<TeamDirectorsView>> findPendingTeamDirectorsByTeamId(@Param("teamId") Long teamId);
}
    