package sts.backend.core_app.persistence;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.persistence.repositories.MatchRepository;
import sts.backend.core_app.persistence.repositories.PlayerRepository;
import sts.backend.core_app.persistence.repositories.PlayerSessionRepository;
import sts.backend.core_app.persistence.repositories.RegistrationCodeRepository;
import sts.backend.core_app.persistence.repositories.SessionRepository;
import sts.backend.core_app.persistence.repositories.TeamDirectorRepository;
import sts.backend.core_app.persistence.repositories.TeamRepository;
import sts.backend.core_app.persistence.repositories.TrainerRepository;
import sts.backend.core_app.persistence.repositories.UserRepository;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;
import sts.backend.core_app.models.Trainer;

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
    private final RegistrationCodeRepository registrationCodeRepository;

    public RelationalQueries(UserRepository userRepository, TeamRepository teamRepository, PlayerRepository playerRepository, TeamDirectorRepository teamDirectorRepository, TrainerRepository trainerRepository, MatchRepository matchRepository, SessionRepository sessionRepository, PlayerSessionRepository playerSessionRepository, RegistrationCodeRepository registrationCodeRepository) {
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.teamDirectorRepository = teamDirectorRepository;
        this.trainerRepository = trainerRepository;
        this.matchRepository = matchRepository;
        this.sessionRepository = sessionRepository;
        this.playerSessionRepository = playerSessionRepository;
        this.registrationCodeRepository = registrationCodeRepository;
    }

    public Set<SessionInfoView> getSessionsInfoByTeam(Team team) {
        return sessionRepository.findSessionInfoByTeam(team);
    }

    public Session createSession(Session result) {
        return sessionRepository.save(result);
    }

    public Match createMatch(Match match) {
        return matchRepository.save(match);
    }

    public Trainer getTrainerById(Long trainerId) throws ResourceNotFoundException {
        return trainerRepository.findById(trainerId)
            .orElseThrow(() -> new ResourceNotFoundException("Trainer with ID " + trainerId + " not found"));
    }

    public Team getTeamById(Long teamId) throws ResourceNotFoundException {
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new ResourceNotFoundException("Team with ID " + teamId + " not found"));
    }

    public RegistrationCode createRegistrationCode(RegistrationCode registrationCode) {
        return registrationCodeRepository.save(registrationCode);
    }

    public RegistrationCode getRegistrationCode(String code) throws ResourceNotFoundException {
        return registrationCodeRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Registration code with code " + code + " not found"));
    }

}
