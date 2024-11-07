package sts.backend.core_app.dto.player;

import java.time.LocalDateTime;

public interface ValueTimeSeriesView {
    Double getValue();
    LocalDateTime getIdTimestamp();
}
