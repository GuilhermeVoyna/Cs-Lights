package org.example.csgsi;
import org.example.utils.json.JsonField;

public class GameState {
    @JsonField("map")
    private MapState        mapState;
    @JsonField("player")
    private PlayerState     playerState;
    @JsonField("provider")
    private ProviderState   providerState;
    @JsonField("round")
    private RoundState      roundState;
    @JsonField("auth")
    private AuthState       authState;

    public GameState(MapState mapState, PlayerState playerState, ProviderState providerState, RoundState roundState, AuthState authState) {
        this.mapState = mapState;
        this.playerState = playerState;
        this.providerState = providerState;
        this.roundState = roundState;
        this.authState = authState;
    }

    public MapState getMapState() {
        return mapState;
    }

    public void setMapState(MapState mapState) {
        this.mapState = mapState;
    }

    public PlayerState getPlayerSate() {
        return playerState;
    }

    public void setPlayerSate(PlayerState playerState) {
        this.playerState = playerState;
    }


    public ProviderState getProviderState() {
        return providerState;
    }

    public void setProviderState(ProviderState providerState) {
        this.providerState = providerState;
    }

    public RoundState getRoundState() {
        return roundState;
    }

    public void setRoundState(RoundState roundState) {
        this.roundState = roundState;
    }

    public AuthState getAuthState() {
        return authState;
    }

    public void setAuthState(AuthState authState) {
        this.authState = authState;
    }
}
