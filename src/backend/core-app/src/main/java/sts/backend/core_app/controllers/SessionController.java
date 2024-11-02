package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.dto.SessionRequest;
import sts.backend.core_app.exceptions.ResourceNotFoundException;
import sts.backend.core_app.models.Session;
import sts.backend.core_app.services.SessionService;

@RestController
@RequestMapping("/api/v1")
public class SessionController {
    
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
    public Set<SessionInfoView> api_list_sessions(@RequestParam(value="team", required=true) Long teamId) throws ResourceNotFoundException {
        return sessionService.getSessionsInfoByTeamId(teamId);
    }

    @PostMapping("/sessions")
    public Session api_create_session(@RequestBody SessionRequest sessionRequest) throws ResourceNotFoundException {
        return sessionService.createSession(sessionRequest);
    }

}
