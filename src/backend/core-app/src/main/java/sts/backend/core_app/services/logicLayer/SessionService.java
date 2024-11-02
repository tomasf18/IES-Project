package sts.backend.core_app.services.logicLayer;

import java.util.Set;

import org.springframework.stereotype.Service;

import sts.backend.core_app.dto.SessionInfoView;
import sts.backend.core_app.persistence.RelationalQueries;

@Service
public class SessionService {

    private final RelationalQueries relationalQueries;

    public SessionService(RelationalQueries relationalQueries) {
        this.relationalQueries = relationalQueries;
    }

    public Set<SessionInfoView> getSessionsInfoByTeamId(Long teamId) {
        return relationalQueries.getSessionsInfoByTeamId(teamId);
    }

}