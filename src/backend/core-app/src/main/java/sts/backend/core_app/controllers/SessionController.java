package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.session.AssignSessionPlayer;
import sts.backend.core_app.dto.session.HistoricalExtraDetailsResponse;
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
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToTrainer(#sessionRequest.getTrainerId())")
    public Session api_create_session(@RequestBody SessionRequest sessionRequest) throws ResourceNotFoundException {
        return sessionService.createSession(sessionRequest);
    }

    @PostMapping("/sessions/match")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('COACH') and @securityService.hasAccessToTrainer(#sessionRequest.getTrainerId()))")
    public Session api_create_match_session(@RequestBody MatchRequest matchRequest) throws ResourceNotFoundException {
        return sessionService.createMatch(matchRequest);
    }

    @PostMapping("/sessions/end")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public Session api_end_session(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.endSession(sessionId);
    }

    @PostMapping("/sessions/assign-player")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToNewSession(#assignSessionPlayer.getSessionId())")
    public PlayerSession api_assign_player(@RequestBody AssignSessionPlayer assignSessionPlayer) throws ResourceNotFoundException {
        return sessionService.assignPlayer(assignSessionPlayer);
    }

    @GetMapping("/sessions/team")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('COACH') and @securityService.hasAccessToTeam(#teamId))")
    public Set<SessionInfoView> api_list_sessions_team(@RequestParam Long teamId) throws ResourceNotFoundException {
        return sessionService.getSessionsInfoByTeamId(teamId);
    }

    @GetMapping("/sessions/player")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToPlayer(#playerId)")
    public Set<SessionInfoView> api_list_sessions_player(@RequestParam Long playerId) throws ResourceNotFoundException {
        return sessionService.getSessionsInfoByPlayerId(playerId);
    }

    @GetMapping("/sessions/real-time-info")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public RealTimeInfoResponse api_get_real_time_info(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getRealTimeInfo(sessionId);
    }

    @GetMapping("/sessions/real-time-info-trainer")
    // @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#trainerId)")
    public RealTimeInfoResponse api_get_real_time_info_trainer(@RequestParam Long trainerId) throws ResourceNotFoundException {
        return sessionService.getRealTimeInfoTrainer(trainerId);
    }

    @GetMapping("/sessions/real-time-extra-details")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public RealTimeExtraDetailsResponse api_get_real_time_extra_details(@RequestParam Long sessionId, @RequestParam Long playerId) throws ResourceNotFoundException {
        return sessionService.getRealTimeExtraDetails(sessionId, playerId);
    }

    @GetMapping("/sessions/historical-info")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public HistoricalExtraDetailsResponse api_get_historical_info(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getHistoricalInfo(sessionId);
    }

    @GetMapping("/sessions/historical-extra-details")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public HistoricalExtraDetailsResponse api_get_historical_extra_details(@RequestParam Long sessionId, @RequestParam Long playerId) throws ResourceNotFoundException {
        return sessionService.getHistoricalExtraDetails(sessionId, playerId);
    }

    @GetMapping("/sessions/notifications")
    @PreAuthorize("hasRole('ADMIN') or @securityService.hasAccessToSession(#sessionId)")
    public Set<NotificationResponse> api_get_notifications(@RequestParam Long sessionId) throws ResourceNotFoundException {
        return sessionService.getNotifications(sessionId);
    }
}
