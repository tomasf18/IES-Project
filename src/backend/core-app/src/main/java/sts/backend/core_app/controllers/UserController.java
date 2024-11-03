package sts.backend.core_app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.IdLong;
import sts.backend.core_app.dto.UserCreationInfo;
import sts.backend.core_app.dto.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.services.business.UserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public UserCreationInfo api_create_player(@RequestBody UserSignUp userSignUp) throws ResourceNotFoundException {
        return userService.createUser(userSignUp);
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestBody IdLong idLong) throws ResourceNotFoundException {
        userService.deleteUser(idLong.getId());
        return ResponseEntity.noContent().build();
    }


}
