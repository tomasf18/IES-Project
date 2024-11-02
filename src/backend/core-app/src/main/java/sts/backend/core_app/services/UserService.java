package sts.backend.core_app.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import sts.backend.core_app.models.User;
import sts.backend.core_app.analysis.interfaces.BasicDataAnalysis;
import sts.backend.core_app.dto.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;

@Service
public class UserService {
    
    private final TeamService teamService;
    private final BasicDataAnalysis basicDataAnalysis;
    
    public UserService(TeamService teamService, BasicDataAnalysis basicDataAnalysis) {
        this.teamService = teamService;
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public User createUser(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.getRegistrationCode(userSignUp.getCode()); // if not found: ResourceNotFoundException
        
        if (code.isUsed()) {
            throw new ResourceNotFoundException("Code " + code.getCode() + " already used");
        }
        if (code.getExpirationTime().isBefore(LocalDate.now())) {
            throw new ResourceNotFoundException("Code " + code.getCode() + " expired");
        }

        // create user
        User user;
        
        if (code.getUserTypeId() == 1) {
            // create player
            user = new Player();
            ((Player) user).setTeam(code.getTeam());
            ((Player) user).setActiveSensorId(null);

        } else if (code.getUserTypeId() == 2) {
            // create team director
            user = new TeamDirector();
            ((TeamDirector) user).setTeam(code.getTeam());

        } else if (code.getUserTypeId() == 3) {
            // create trainer
            user = new Trainer();
            ((Trainer) user).setTeam(code.getTeam());

        } else {
            throw new ResourceNotFoundException("User type not found");
        }
        
        user.setName(code.getName());
        user.setProfilePictureUrl(code.getProfilePictureUrl());
        user.setUsername(userSignUp.getUsername());
        user.setEmail(userSignUp.getEmail());
        user.setPassword(userSignUp.getPassword());

        if (code.getUserTypeId() == 1) {
            // create player
            return basicDataAnalysis.createPlayer((Player) user);

        } else if (code.getUserTypeId() == 2) {
            // create team director
            return basicDataAnalysis.createTeamDirector((TeamDirector) user);

        } else if (code.getUserTypeId() == 3) {
            // create trainer
            return basicDataAnalysis.createTrainer((Trainer) user);
        }
        
        return null;
    }

}
