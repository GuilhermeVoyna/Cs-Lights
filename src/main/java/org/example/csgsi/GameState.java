package org.example.csgsi;
import org.example.utils.json.JsonField;

public class GameState {
    @JsonField("map")
    private MapState        MapState;
    @JsonField("player")
    private PlayerSate      player;
    //private ProviderState   provider;
    //private RoundState      round;
    //private AuthState       auth;


    public GameState(MapState mapState, PlayerSate player) {
        MapState = mapState;
        this.player = player;
    }

    public MapState getMap() {
        return MapState;
    }

    public void setMap(MapState map) {
        this.MapState = map;
    }
}
