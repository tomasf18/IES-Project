package sts.backend.core_app.dto.player;

import java.time.LocalDate;
import java.util.Map;

public class SessionsAllDayOfYear {
    private Long playerId;
    private Long year;
    private Map<LocalDate, Integer> sessionsPerDay;

    public SessionsAllDayOfYear(Long playerId, Long year, Map<LocalDate, Integer> sessionsPerDay) {
        this.playerId = playerId;
        this.year = year;
        this.sessionsPerDay = sessionsPerDay;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getYear() {
        return year;
    }

    public Map<LocalDate, Integer> getSessionsPerDay() {
        return sessionsPerDay;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public void setSessionsPerDay(Map<LocalDate, Integer> sessionsPerDay) {
        this.sessionsPerDay = sessionsPerDay;
    }
}
