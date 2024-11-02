package sts.backend.core_app.dto;

public class TeamCreation {
    
    private String name;
    private String teamDirectorName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamDirectorName() {
        return teamDirectorName;
    }

    public void setTeamDirectorName(String teamDirectorName) {
        this.teamDirectorName = teamDirectorName;
    }
}
