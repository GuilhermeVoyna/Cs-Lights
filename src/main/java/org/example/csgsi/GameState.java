package org.example.csgsi;
import org.example.utils.json.JsonField;

public class GameState {
    @JsonField("map")
    private MapState        mapState;
    @JsonField("player")
    private PlayerSate      player;
    @JsonField("state")
    private SelfState       self;
    @JsonField("provider")
    private ProviderState   provider;
    @JsonField("round")
    private RoundState      round;
    @JsonField("auth")
    private AuthState       auth;

    public GameState() {
        this.mapState   = null;
        this.player     = null;
        this.self       = null;
        this.provider   = null;
        this.round      = null;
        this.auth       = null;
    }

    public GameState(MapState mapState, PlayerSate player, SelfState self, ProviderState provider, RoundState round, AuthState auth) {
        this.mapState = mapState;
        this.player = player;
        this.self = self;
        this.provider = provider;
        this.round = round;
        this.auth = auth;
    }

    public MapState getMapState() {
        return mapState;
    }

    public void setMapState(MapState mapState) {
        this.mapState = mapState;
    }

    public PlayerSate getPlayer() {
        return player;
    }

    public void setPlayer(PlayerSate player) {
        this.player = player;
    }

    public SelfState getSelf() {
        return self;
    }

    public void setSelf(SelfState self) {
        this.self = self;
    }

    public ProviderState getProvider() {
        return provider;
    }

    public void setProvider(ProviderState provider) {
        this.provider = provider;
    }

    public RoundState getRound() {
        return round;
    }

    public void setRound(RoundState round) {
        this.round = round;
    }

    public AuthState getAuth() {
        return auth;
    }

    public void setAuth(AuthState auth) {
        this.auth = auth;
    }
}
