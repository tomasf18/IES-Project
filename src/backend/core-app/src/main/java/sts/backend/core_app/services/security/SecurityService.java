package sts.backend.core_app.services.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.Sensor;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;

@Service
public class SecurityService {

    private final BasicDataAnalysis basicDataAnalysis;

    public SecurityService(BasicDataAnalysis basicDataAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public boolean hasAccessToUser(Long userId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = basicDataAnalysis.getUserByUsername(currentUsername);

        if (user == null || !user.getRoles().contains("PLAYER")) {
            return false;
        }

        return user.getUserId().equals(userId);
    }

    public boolean hasAccessToTrainer(Long trainerId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = basicDataAnalysis.getUserByUsername(currentUsername);

        if (user == null) {
            return false;
        }

        if (! (user.getRoles().contains("COACH") || user.getRoles().contains("PERSONAL_TRAINER"))) {
            return false;
        }

        return user.getUserId().equals(trainerId);
    }

    public boolean hasAccessToSession(Long sessionId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Trainer trainer = basicDataAnalysis.getTrainerByUsername(currentUsername);

        if (trainer == null) {
            return false;
        }

        Session session = basicDataAnalysis.getSessionById(sessionId);

        if (session == null) {
            return false;
        }

        // Coaches can access any session of their team
        if (trainer.getRoles().contains("COACH")) {
            return session.getTrainer().getTeam().getTeamId().equals(trainer.getTeam().getTeamId());
        }
        else if (trainer.getRoles().contains("PERSONAL_TRAINER")) {
            return session.getTrainer().getUserId().equals(trainer.getUserId());
        }
        
        return false;
    }

    public boolean hasAccessToNewSession(Long sessionId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Trainer trainer = basicDataAnalysis.getTrainerByUsername(currentUsername);

        if (trainer == null) {
            return false;
        }

        Session session = basicDataAnalysis.getSessionById(sessionId);

        if (session == null || !session.getEndTime().equals(null)) {
            return false;
        }

        return session.getTrainer().getUserId().equals(trainer.getUserId());
    }

    public boolean hasAccessToTeam(Long teamId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Trainer trainer = basicDataAnalysis.getTrainerByUsername(currentUsername);

        if (trainer == null) {
            return false;
        }

        return trainer.getTeam().getTeamId().equals(teamId);
    }

    public boolean hasAccessToPlayer(Long playerId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Player player = basicDataAnalysis.getPlayerByUsername(currentUsername);

        if (player == null) {
            return false;
        }

        return player.getUserId().equals(playerId);
    }

    public boolean hasAccessToModerateTeam(Long teamId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        TeamDirector teamDirector = basicDataAnalysis.getTeamDirectorByUsername(currentUsername);

        if (teamDirector == null) {
            return false;
        }

        return teamDirector.getTeam().getTeamId().equals(teamId);
    }

    public boolean hasAccessToModerateCode(String code) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        TeamDirector teamDirector = basicDataAnalysis.getTeamDirectorByUsername(currentUsername);

        if (teamDirector == null) {
            return false;
        }

        RegistrationCode registrationCode = basicDataAnalysis.getRegistrationCode(code);

        if (registrationCode == null) {
            return false;
        }

        return registrationCode.getTeam().getTeamId().equals(teamDirector.getTeam().getTeamId());
    }

    public boolean hasAccessToSensorOfTeam(Long teamId) throws ResourceNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User user = basicDataAnalysis.getUserByUsername(currentUsername);

        if (user == null) {
            return false;
        }

        if (user.getRoles().contains("TEAM_DIRECTOR")) {
            TeamDirector teamDirector = basicDataAnalysis.getTeamDirectorByUsername(currentUsername);
            return teamDirector.getTeam().getTeamId().equals(teamId);
        }
        else if (user.getRoles().contains("COACH") || user.getRoles().contains("PERSONAL_TRAINER")) {
            Trainer trainer = basicDataAnalysis.getTrainerByUsername(currentUsername);
            return trainer.getTeam().getTeamId().equals(teamId);
        }

        return false;
    }

    public boolean hasAccessToModerateSensor(Long sensorId) throws ResourceNotFoundException {
        Sensor sensor = basicDataAnalysis.getSensorById(sensorId);
        return hasAccessToSensorOfTeam(sensor.getTeam().getTeamId());
    }

}
