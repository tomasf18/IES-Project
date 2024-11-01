package sts.backend.core_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "User: name is mandatory")
    @Size(max = 50, message = "User: name must be at most 50 characters")
    private String name;

    @NotBlank(message = "User: username is mandatory")
    @Size(max = 50, message = "User: username must be at most 50 characters")
    @Column(unique=true)
    private String username;

    @NotBlank(message = "User: email is mandatory")
    @Size(max = 100, message = "User: email must be at most 100 characters")
    @Email(message = "User: email should be valid")
    @Column(unique=true)
    private String email;

    @NotBlank(message = "User: password is mandatory")
    @Size(min = 8, max = 100, message = "User: password must be between 8 and 100 characters")
    private String password;

    @Size(max = 255, message = "User: profilePictureUrl must be at most 255 characters")
    private String profilePictureUrl;

    @NotBlank(message = "User: userTypeId is mandatory")
    private Long userTypeId;

    // standard constructors / setters / getters / toString
    public User() {}

    public User(String name, String username, String email, String password, String profilePictureUrl, Long userTypeId) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePictureUrl = profilePictureUrl;
        this.userTypeId = userTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Long getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", userTypeId=" + userTypeId +
                '}';
    }
    
}
