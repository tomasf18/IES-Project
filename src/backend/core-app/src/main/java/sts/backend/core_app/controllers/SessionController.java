package sts.backend.core_app.controllers;

import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.services.logicLayer.SessionService;

@RestController
@RequestMapping("/api/v1")
public class SessionController {
    
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/sessions")
	public Set<SessionInfoView> api_movies(@RequestParam(value="team", required=false) Long teamId) {
        return sessionService.getSessionsInfoByTeamId(teamId);
	}

}
