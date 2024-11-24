package sts.backend.core_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.persistence.repositories.postgreDB.UserRepository;
import sts.backend.core_app.services.business.AuthService;

// swagger moved to /config/OpenApiConfig.java, for JWT security
@SpringBootApplication(scanBasePackages = "sts.backend.core_app")
public class CoreAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreAppApplication.class, args);
	}

    // TODO: remove this method when deploying to production
    @Bean
    CommandLineRunner init(AuthService authService, UserRepository userRepository) {
        return args -> {
            try {   
                authService.createAdminUser(userRepository, "admin", "admin", "admin@ua.pt", "admin");
            } catch (ResourceNotFoundException e) {
                System.out.println("Admin user already exists or there was an error: " + e.getMessage());
            }
        };
    }

}
