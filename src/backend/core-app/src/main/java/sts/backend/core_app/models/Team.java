package sts.backend.core_app.models;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;

@Entity(name = "teams")
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;

    @NotBlank(message = "Team: name is mandatory")
    @Size(max = 50, message = "Team: name must be at most 50 characters")
    private String name;

    
    
}
