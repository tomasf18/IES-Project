package sts.backend.core_app.dto.admin;

public class SensorsTeamWeek {
    private String teamName;
    private String state;
    private int value;

    public SensorsTeamWeek() {
    }

    public SensorsTeamWeek(String teamName, String state, int value) {
        this.teamName = teamName;
        this.state = state;
        this.value = value;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getState() {
        return state;
    }

    public int getValue() {
        return value;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
