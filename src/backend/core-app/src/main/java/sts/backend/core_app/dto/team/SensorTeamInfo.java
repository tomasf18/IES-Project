package sts.backend.core_app.dto.team;

public class SensorTeamInfo {
    private Long sensorId;
    private Long teamId;

    public Long getSensorId() {
        return sensorId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

}
