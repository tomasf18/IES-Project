package sts.backend.core_app.persistence;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.persistence.repositories.MatchRepository;
import sts.backend.core_app.persistence.repositories.PlayerRepository;
import sts.backend.core_app.persistence.repositories.PlayerSessionRepository;
import sts.backend.core_app.persistence.repositories.SessionRepository;
import sts.backend.core_app.persistence.repositories.TeamDirectorRepository;
import sts.backend.core_app.persistence.repositories.TeamRepository;
import sts.backend.core_app.persistence.repositories.TrainerRepository;
import sts.backend.core_app.persistence.repositories.UserRepository;

import sts.backend.core_app.dto.SessionInfoView;

@Service
public class RelationalQueries {
    
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final TeamDirectorRepository teamDirectorRepository;
    private final TrainerRepository trainerRepository;
    private final MatchRepository matchRepository;
    private final SessionRepository sessionRepository;
    private final PlayerSessionRepository playerSessionRepository;

    public RelationalQueries(UserRepository userRepository, TeamRepository teamRepository, PlayerRepository playerRepository, TeamDirectorRepository teamDirectorRepository, TrainerRepository trainerRepository, MatchRepository matchRepository, SessionRepository sessionRepository, PlayerSessionRepository playerSessionRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamDirectorRepository = teamDirectorRepository;
        this.trainerRepository = trainerRepository;
        this.matchRepository = matchRepository;
        this.sessionRepository = sessionRepository;
        this.playerSessionRepository = playerSessionRepository;
    }

    public Set<SessionInfoView> getSessionsInfoByTeamId(Long teamId) {
        return sessionRepository.findByTrainer_TeamId(teamId);
    }

}
