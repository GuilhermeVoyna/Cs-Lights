package org.example.csgsi;
import org.example.utils.json.JsonField;

public class GameState {
    @JsonField("map")
    private MapState        MapState;
    @JsonField("test")
    private Object          object;
    //private PlayerState     player;
    //private ProviderState   provider;
    //private RoundState      round;
    //private AuthState       auth;


    public GameState(MapState mapState, Object object) {
        MapState = mapState;
        this.object = object;
    }

    public MapState getMap() {
        return MapState;
    }

    public void setMap(MapState map) {
        this.MapState = map;
    }
}
