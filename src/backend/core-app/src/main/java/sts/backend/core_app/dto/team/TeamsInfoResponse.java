package sts.backend.core_app.dto.team;

public class TeamsInfoResponse {
    private Long teamId;
    private String name;
    private Long numberOfMembers;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Long numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
}