package sts.backend.core_app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.AuthRequest;
import sts.backend.core_app.dto.AuthResponse;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.business.AuthService;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/sign-up/validation")
    public AuthResponse api_sign_up_validation(@RequestBody AuthRequest authRequest) throws ResourceNotFoundException {
        return authService.signUpValidation(authRequest); // TODO: implement
    }

    @PostMapping("/auth/sign-in")
    public AuthResponse api_sign_in(@RequestBody AuthRequest authRequest) throws ResourceNotFoundException {
        return authService.signIn(authRequest); // TODO: implement
    }
    
}
