package sts.backend.core_app.services.business;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.auth.AuthRequest;
import sts.backend.core_app.dto.auth.AuthResponse;
import sts.backend.core_app.dto.auth.InputValidationRequest;
import sts.backend.core_app.dto.user.UserCreationInfo;
import sts.backend.core_app.dto.user.UserSignUp;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Player;
import sts.backend.core_app.models.RegistrationCode;
import sts.backend.core_app.models.TeamDirector;
import sts.backend.core_app.models.Trainer;
import sts.backend.core_app.models.User;
import sts.backend.core_app.persistence.repositories.postgreDB.UserRepository;
import sts.backend.core_app.security.JwtTokenUtil;
import sts.backend.core_app.services.analysis.interfaces.BasicDataAnalysis;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private JwtTokenUtil jwtTokenUtil;
    private final BasicDataAnalysis basicDataAnalysis;

    public AuthService(BasicDataAnalysis basicDataAnalysis, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.basicDataAnalysis = basicDataAnalysis;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public UserCreationInfo createUser(UserSignUp userSignUp, RegistrationCode code) throws ResourceNotFoundException {
        UserCreationInfo userCreationInfo = new UserCreationInfo();
        
        User user = null;
        Boolean isCoach = false;

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
                isCoach = true;
                Trainer coach = createTrainer(userSignUp, code, isCoach);
                userCreationInfo.setTeam(coach.getTeam());
                user = coach;
                break;
            case 4:
                Trainer personalTrainer = createTrainer(userSignUp, code, isCoach);
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
        player.setName(code.getName());
        player.setProfilePictureUrl(code.getProfilePictureUrl());
        player.setUsername(userSignUp.getUsername());
        player.setEmail(userSignUp.getEmail());
        player.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
        player.setRoles(Set.of("ROLE_PLAYER"));
        return basicDataAnalysis.createPlayer(player);
    }

    public TeamDirector createTeamDirector(UserSignUp userSignUp, RegistrationCode code) throws ResourceNotFoundException {
        TeamDirector teamDirector = new TeamDirector();
        teamDirector.setTeam(code.getTeam());
        teamDirector.setName(code.getName());
        teamDirector.setProfilePictureUrl(code.getProfilePictureUrl());
        teamDirector.setUsername(userSignUp.getUsername());
        teamDirector.setEmail(userSignUp.getEmail());
        teamDirector.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
        teamDirector.setRoles(Set.of("ROLE_TEAM_DIRECTOR"));
        return basicDataAnalysis.createTeamDirector(teamDirector);
    }

    public Trainer createTrainer(UserSignUp userSignUp, RegistrationCode code, Boolean isCoach) throws ResourceNotFoundException {
        Trainer trainer = new Trainer();
        trainer.setTeam(code.getTeam());
        trainer.setName(code.getName());
        trainer.setProfilePictureUrl(code.getProfilePictureUrl());
        trainer.setUsername(userSignUp.getUsername());
        trainer.setEmail(userSignUp.getEmail());
        trainer.setPassword(passwordEncoder.encode(userSignUp.getPassword()));
        trainer.setIsCoach(isCoach);
        if (isCoach) {
            trainer.setRoles(Set.of("ROLE_COACH"));
        } else {
            trainer.setRoles(Set.of("ROLE_PERSONAL_TRAINER"));
        }
        return basicDataAnalysis.createTrainer(trainer);
    }



    public AuthResponse signIn(AuthRequest authRequest) throws ResourceNotFoundException {
        String usernameOrEmail = authRequest.getUsernameOrEmail();
        User user = null;

        if (usernameOrEmail.contains("@")) {
            // email
            user = basicDataAnalysis.getUserByEmail(usernameOrEmail);
        } else {
            // username
            user = basicDataAnalysis.getUserByUsername(usernameOrEmail);
        }
        
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid password");
        }

        String token = jwtTokenUtil.generateToken(user.getUsername(), user.getRoles());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUserId(user.getUserId());
        authResponse.setName(user.getName());
        authResponse.setUsername(user.getUsername());
        authResponse.setEmail(user.getEmail());
        authResponse.setProfilePictureUrl(user.getProfilePictureUrl());
        authResponse.setRoles(user.getRoles());

        return authResponse;
    }

    public boolean signUpValidation(InputValidationRequest inputValidationRequest) throws ResourceNotFoundException {
        boolean inputValid = true;
        
        // +- uniform response time, due to security reasons
        try {
            basicDataAnalysis.getUserByUsername(inputValidationRequest.getUsername());
            inputValid = false;
        } catch (ResourceNotFoundException e) {
            // continue
        }

        try {
            basicDataAnalysis.getUserByEmail(inputValidationRequest.getEmail());
            inputValid = false;
        } catch (ResourceNotFoundException e) {
            // continue
        }

        return inputValid;
    }

    // TODO: remove when deploying to production
    public User createAdminUser(UserRepository userRepository, String name, String username, String email, String password) throws ResourceNotFoundException {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new ResourceNotFoundException("Admin already exists");
        }

        User admin = new User();
        admin.setName(name);
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPassword(passwordEncoder.encode(password)); // Encode password
        admin.setRoles(Set.of("ROLE_ADMIN")); // Set admin role

        return userRepository.save(admin);
    }
    
}
