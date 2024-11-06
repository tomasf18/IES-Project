package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.session.AssignSessionPlayer;
import sts.backend.core_app.dto.session.HistoricalExtraDetailsResponse;
import sts.backend.core_app.dto.session.HistoricalInfoResponse;
import sts.backend.core_app.dto.session.MatchRequest;
import sts.backend.core_app.dto.session.NotificationResponse;
import sts.backend.core_app.dto.session.RealTimeExtraDetailsResponse;
import sts.backend.core_app.dto.session.RealTimeInfoResponse;
import sts.backend.core_app.dto.session.SessionInfoView;
import sts.backend.core_app.dto.session.SessionRequest;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.PlayerSession;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.services.business.SessionService;

@RestController
@RequestMapping("/api/v1")
public class SessionController {
    
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/sessions")
    public Session api_create_session(@RequestBody SessionRequest sessionRequest) throws ResourceNotFoundException {
        return sessionService.createSession(sessionRequest);
    }

    @PostMapping("/sessions/match")
    public Session api_create_match_session(@RequestParam MatchRequest matchRequest) throws ResourceNotFoundException {
        return sessionService.createMatch(matchRequest);
    }

    @PostMapping("/sessions/end")
    public Session api_end_session(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.endSession(sessionId);
    }

    @PostMapping("/sessions/assign-player")
    public PlayerSession api_assign_player(@RequestBody AssignSessionPlayer assignSessionPlayer) throws ResourceNotFoundException {
        return sessionService.assignPlayer(assignSessionPlayer);
    }

    @GetMapping("/sessions/team")
    public Set<SessionInfoView> api_list_sessions_team(@RequestParam Long teamId) throws ResourceNotFoundException {
        return sessionService.getSessionsInfoByTeamId(teamId);
    }

    @GetMapping("/sessions/player")
    public Set<SessionInfoView> api_list_sessions_player(@RequestParam Long playerId) throws ResourceNotFoundException {
        return sessionService.getSessionsInfoByPlayerId(playerId);
    }

    @GetMapping("/sessions/real-time-info")
    public RealTimeInfoResponse api_get_real_time_info(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getRealTimeInfo(sessionId);
    }

    @GetMapping("/sessions/real-time-extra-details")
    public RealTimeExtraDetailsResponse api_get_real_time_extra_details(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getRealTimeExtraDetails(sessionId);
    }

    @GetMapping("/sessions/historical-info")
    public HistoricalInfoResponse api_get_historical_info(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getHistoricalInfo(sessionId);
    }

    @GetMapping("/sessions/historical-extra-details")
    public HistoricalExtraDetailsResponse api_get_historical_extra_details(@RequestParam Long sessionId, @RequestParam Long playerId) throws ResourceNotFoundException {
        return sessionService.getHistoricalExtraDetails(sessionId, playerId);
    }

    @GetMapping("/sessions/notifications")
    public Set<NotificationResponse> api_get_notifications(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getNotifications(sessionId);
    }


}
