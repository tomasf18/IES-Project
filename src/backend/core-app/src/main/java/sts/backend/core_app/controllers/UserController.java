package sts.backend.core_app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.User;
import sts.backend.core_app.services.business.UserService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> api_get_users() throws ResourceNotFoundException {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/users")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEAM_DIRECTOR')")
    public ResponseEntity<?> api_delete_user(@RequestParam Long userId) throws ResourceNotFoundException {
        userService.deleteUser(userId); 
        return ResponseEntity.ok().build();
    }

}