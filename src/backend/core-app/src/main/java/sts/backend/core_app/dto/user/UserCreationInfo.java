package sts.backend.core_app.dto.user;

import sts.backend.core_app.models.Team;

public class UserCreationInfo {
    private Long userId;
    private String name;
    private String username;
    private String email;
    private String profilePictureUrl;
    private Team team;
    private Long userTypeId;
    
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
    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }
    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
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

    

}
