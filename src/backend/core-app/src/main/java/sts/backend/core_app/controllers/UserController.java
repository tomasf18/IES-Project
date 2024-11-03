package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.User;
import sts.backend.core_app.services.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/administrator")
    public User api_create_administrator(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        return userService.createAdministrator(userSignUp);
    }

    @PostMapping("/users/player")
    public User api_create_player(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        return userService.createPlayer(userSignUp);
    }

    @PostMapping("/users/team-director")
    public User api_create_team_director(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        return userService.createTeamDirector(userSignUp);
    }

    @PostMapping("/users/trainer")
    public User api_create_trainer(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        return userService.createTrainer(userSignUp);
    }

}
