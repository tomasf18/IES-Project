package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.auth.AuthRequest;
import sts.backend.core_app.dto.auth.AuthResponse;
import sts.backend.core_app.dto.auth.InputValidationRequest;
import sts.backend.core_app.dto.user.UserCreationInfo;
import sts.backend.core_app.dto.user.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.services.business.AuthService;
import sts.backend.core_app.services.business.TeamService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    
    private final AuthService authService;
    private final TeamService teamService;

    public AuthController(AuthService authService, TeamService teamService) {
        this.authService = authService;
        this.teamService = teamService;
    }

    @GetMapping("/auth/me")
    public AuthResponse api_auth_me() throws ResourceNotFoundException {
        return authService.me(); // with token from cookie
    }
    
    @PostMapping("/auth/sign-in")
    public AuthResponse api_sign_in(@RequestBody AuthRequest authRequest) throws ResourceNotFoundException {
        return authService.signIn(authRequest); 
    }

    @PostMapping("/auth/sign-up/validation")
    public boolean api_sign_up_validation(@RequestBody InputValidationRequest inputValidationRequest) throws ResourceNotFoundException {
        return authService.signUpValidation(inputValidationRequest);
    }

    @PostMapping("/auth/sign-up")
    public UserCreationInfo api_create_user(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        RegistrationCode registrationCode = teamService.claimRegistrationCode(userSignUp.getCode()); // claim, even if it's not valid
        return authService.createUser(userSignUp, registrationCode);
    }
    
}
