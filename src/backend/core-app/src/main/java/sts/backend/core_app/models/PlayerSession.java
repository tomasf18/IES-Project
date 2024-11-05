package sts.backend.core_app.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name = "playerSessions")
public class PlayerSession {
    
    @EmbeddedId
    private PlayerSessionId id;

    @ManyToOne
    @JoinColumn(name = "PLAYER_FK", insertable = false, updatable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "SESSION_FK", insertable = false, updatable = false)
    private Session session;

    private Long sensorId;
    
    // standard constructors / setters / getters / toString
    public PlayerSession() {}

    public PlayerSession(Player player, Session session, Long sensorId, PlayerSessionId id) {
        this.player = player;
        this.session = session;
        this.sensorId = sensorId;
        this.id = id;
    }

    public PlayerSessionId getId() {
        return id;
    }

    public void setId(PlayerSessionId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

}
