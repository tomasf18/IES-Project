package sts.backend.core_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity(name = "matches")
public class Match extends Session {

    @Size(max = 50, message = "Match: opponent team must be at most 50 characters")
    private String opponentTeam;

    @Size(max = 50, message = "Match: match type must be at most 50 characters")
    private String type;

    @Size(max = 100, message = "Match: match location must be at most 100 characters")
    private String location;

    @Size(max = 50, message = "Match: match weather must be at most 50 characters")
    private String weather;

    private String result = "Not available"; // TODO: ...

    // standard constructors / setters / getters / toString
    public Match() {}

    public Match(String name, LocalDateTime startTime, LocalDateTime endTime, Trainer trainer, String opponentTeam, String type, String location, String weather) {
        super(name, startTime, endTime, trainer);
        this.opponentTeam = opponentTeam;
        this.type = type;
        this.location = location;
        this.weather = weather;
    }

    public String getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(String opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
