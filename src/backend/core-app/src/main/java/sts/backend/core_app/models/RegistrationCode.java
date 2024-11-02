package sts.backend.core_app.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name="registrationCodes")
public class RegistrationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    @NotBlank(message = "RegistrationCode: name is mandatory")
    @Size(max = 50, message = "RegistrationCode: name must be at most 50 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_FK", nullable = false)
    private Team team;

    @NotBlank(message = "RegistrationCode: userTypeId is mandatory")
    private Long userTypeId;

    @Size(max = 255, message = "RegistrationCode: profilePicUrl must be at most 255 characters")
    private String profilePictureUrl;
    
    private boolean isUsed = false;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate expirationTime;

    // standard constructors, getters, setters, etc.
    public RegistrationCode() {}

    public RegistrationCode(String code, String name, Team team, Long userTypeId, String profilePictureUrl, boolean isUsed, LocalDate expirationTime) {
        this.code = code;
        this.name = name;
        this.team = team;
        this.userTypeId = userTypeId;
        this.profilePictureUrl = profilePictureUrl;
        this.isUsed = isUsed;
        this.expirationTime = expirationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public LocalDate getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDate expirationTime) {
        this.expirationTime = expirationTime;
    }

    
}
