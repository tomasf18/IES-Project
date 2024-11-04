package sts.backend.core_app.services.business;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.user.UserCreationInfo;
import sts.backend.core_app.dto.user.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;

@Service
public class UserService {
    
    private final TeamService teamService;
    private final BasicDataAnalysis basicDataAnalysis;
    
    public UserService(TeamService teamService, BasicDataAnalysis basicDataAnalysis) {
        this.teamService = teamService;
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public UserCreationInfo createUser(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.claimRegistrationCode(userSignUp.getCode()); // if not found -> ResourceNotFoundException
        UserCreationInfo userCreationInfo = new UserCreationInfo();
        User user = null;
        
        switch (code.getUserTypeId().intValue()) {
            case 1:
                Player p = createPlayer(userSignUp, code);
                userCreationInfo.setTeam(p.getTeam());
                user = p;
                break;
            case 2:
                TeamDirector td = createTeamDirector(userSignUp, code);
                userCreationInfo.setTeam(td.getTeam());
                user = td;
                break;
            case 3:
                Trainer coach = createTrainer(userSignUp, code);
                userCreationInfo.setTeam(coach.getTeam());
                user = coach;
                break;
            case 4:
                Trainer personalTrainer = createTrainer(userSignUp, code);
                userCreationInfo.setTeam(personalTrainer.getTeam());
                user = personalTrainer;
                break;    
            default:
                throw new ResourceNotFoundException("Invalid user type");
        }
        
        userCreationInfo.setUserId(user.getUserId());
        userCreationInfo.setName(user.getName());
        userCreationInfo.setUsername(user.getUsername());
        userCreationInfo.setEmail(user.getEmail());
        userCreationInfo.setProfilePictureUrl(user.getProfilePictureUrl());
        userCreationInfo.setUserTypeId(code.getUserTypeId());

        return userCreationInfo;
    }

    public Player createPlayer(UserSignUp userSignUp, RegistrationCode code) throws ResourceNotFoundException {
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

    public TeamDirector createTeamDirector(UserSignUp userSignUp, RegistrationCode code) throws ResourceNotFoundException {
        TeamDirector teamDirector = new TeamDirector();
        teamDirector.setTeam(code.getTeam());
        teamDirector.setName(code.getName());
        teamDirector.setProfilePictureUrl(code.getProfilePictureUrl());
        teamDirector.setUsername(userSignUp.getUsername());
        teamDirector.setEmail(userSignUp.getEmail());
        teamDirector.setPassword(userSignUp.getPassword());
        return basicDataAnalysis.createTeamDirector(teamDirector);
    }

    public Trainer createTrainer(UserSignUp userSignUp, RegistrationCode code) throws ResourceNotFoundException {
        Trainer trainer = new Trainer();
        trainer.setTeam(code.getTeam());
        trainer.setName(code.getName());
        trainer.setProfilePictureUrl(code.getProfilePictureUrl());
        trainer.setUsername(userSignUp.getUsername());
        trainer.setEmail(userSignUp.getEmail());
        trainer.setPassword(userSignUp.getPassword());
        return basicDataAnalysis.createTrainer(trainer);
    }

    public void deleteUser(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }
}
