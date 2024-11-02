package sts.backend.core_app.services;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.analysis.BasicDataAnalysis;
import sts.backend.core_app.dto.MatchRequest;
import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.dto.SessionRequest;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Match;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.models.Team;

@Service
public class SessionService {

    private final BasicDataAnalysis basicDataAnalysis;

    public SessionService(BasicDataAnalysis basicDataAnalysis) {
        this.basicDataAnalysis = basicDataAnalysis;
    }

    public Set<SessionInfoView> getSessionsInfoByTeamId(Long teamId) throws ResourceNotFoundException {
        Team team = basicDataAnalysis.getTeamById(teamId);
        return basicDataAnalysis.getSessionsInfoByTeamId(team);
    }

    public Session createSession(SessionRequest sessionRequest) throws ResourceNotFoundException {
        Session session = new Session();
        session.setName(sessionRequest.getName());
        session.setStartTime(LocalDate.now());
        session.setEndTime(null);
        session.setTrainer(basicDataAnalysis.getTrainerById(sessionRequest.getTrainerId()));
        return basicDataAnalysis.createSession(session);
    }

    public Session createMatch(MatchRequest matchRequest) throws ResourceNotFoundException {
        Match match = new Match();
        match.setOpponentTeam(matchRequest.getOpponentTeam());
        match.setType(matchRequest.getType());
        match.setLocation(matchRequest.getLocation());
        match.setWeather(matchRequest.getWeather());

        match.setName(matchRequest.getName());
        match.setStartTime(LocalDate.now());
        match.setEndTime(null);
        match.setTrainer(basicDataAnalysis.getTrainerById(matchRequest.getTrainerId()));

        return basicDataAnalysis.createMatch(match);
    }

}