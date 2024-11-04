package sts.backend.core_app.dto.team;

public class TeamMembersResponse {

    private String name;
    private String profilePictureUrl;
    private Long userTypeId;

    public TeamMembersResponse() {
    }

    public TeamMembersResponse(String name, String profilePictureUrl, Long userTypeId) {
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.userTypeId = userTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
