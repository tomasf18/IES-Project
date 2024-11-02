package sts.backend.core_app.services;

import java.time.LocalDate;

import sts.backend.core_app.models.User;
import sts.backend.core_app.analysis.BasicDataAnalysis;
import sts.backend.core_app.dto.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;

public class UserService {
    
    private final TeamService teamService;
    private final BasicDataAnalysis basicDataAnalysis;
    
    public UserService(TeamService teamService, BasicDataAnalysis basicDataAnalysis) {
        this.teamService = teamService;
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public void signUp(UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode code = teamService.getRegistrationCode(userSignUp.getCode()); // if not found: ResourceNotFoundException
        
        if (code.isUsed()) {
            throw new ResourceNotFoundException("Code " + code.getCode() + " already used");
        }
        if (code.getExpirationTime().isBefore(LocalDate.now())) {
            throw new ResourceNotFoundException("Code " + code.getCode() + " expired");
        }

        // create user
        User user = new User();
        user.setName(code.getName());
        user.setUsername(userSignUp.getUsername());
        user.setEmail(userSignUp.getEmail());
        user.setPassword(userSignUp.getPassword());
        user.setProfilePictureUrl(code.getProfilePictureUrl());
        user.setUserTypeId(code.getUserTypeId());

        basicDataAnalysis.createUser(user);

        // create type of user

        if (code.getUserTypeId() == 1) {
            // create player
        } else if (code.getUserTypeId() == 2) {
            // create trainer
        } else if (code.getUserTypeId() == 3) {
            // create manager
        } else {
            throw new ResourceNotFoundException("User type not found");
        }

    }

}
