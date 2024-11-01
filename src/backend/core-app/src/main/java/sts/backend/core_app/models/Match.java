package sts.backend.core_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "matches")
public class Match {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;

    @Size(max = 50, message = "Match: opponent team must be at most 50 characters")
    private String opponentTeam;

    @Size(max = 50, message = "Match: match type must be at most 50 characters")
    private String type;

    @Size(max = 50, message = "Match: match location must be at most 50 characters")
    private String location;

    @Size(max = 50, message = "Match: match weather must be at most 50 characters")
    private String weather;

    // standard constructors / setters / getters / toString
    public Match() {}

    public Match(String opponentTeam, String type, String location, String weather) {
        this.opponentTeam = opponentTeam;
        this.type = type;
        this.location = location;
        this.weather = weather;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
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

}
