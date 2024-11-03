package sts.backend.core_app.services;

import org.springframework.stereotype.Service;

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

    public Player createPlayer(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.claimRegistrationCode(userSignUp.getCode()); // if not found: ResourceNotFoundException

        // create player
        Player player = new Player();
        player.setTeam(code.getTeam());
        player.setActiveSensorId(null);
        player.setName(code.getName());
        player.setProfilePictureUrl(code.getProfilePictureUrl());
        player.setUsername(userSignUp.getUsername());
        player.setEmail(userSignUp.getEmail());
        player.setPassword(userSignUp.getPassword());

        return basicDataAnalysis.createPlayer(player);
    }

    public TeamDirector createTeamDirector(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.claimRegistrationCode(userSignUp.getCode()); // if not found: ResourceNotFoundException

        // create team director
        TeamDirector teamDirector = new TeamDirector();
        teamDirector.setTeam(code.getTeam());
        teamDirector.setName(code.getName());
        teamDirector.setProfilePictureUrl(code.getProfilePictureUrl());
        teamDirector.setUsername(userSignUp.getUsername());
        teamDirector.setEmail(userSignUp.getEmail());
        teamDirector.setPassword(userSignUp.getPassword());

        return basicDataAnalysis.createTeamDirector(teamDirector);
    }

    public Trainer createTrainer(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.claimRegistrationCode(userSignUp.getCode()); // if not found: ResourceNotFoundException

        // create trainer
        Trainer trainer = new Trainer();
        trainer.setTeam(code.getTeam());
        trainer.setName(code.getName());
        trainer.setProfilePictureUrl(code.getProfilePictureUrl());
        trainer.setUsername(userSignUp.getUsername());
        trainer.setEmail(userSignUp.getEmail());
        trainer.setPassword(userSignUp.getPassword());

        return basicDataAnalysis.createTrainer(trainer);
    }
}
