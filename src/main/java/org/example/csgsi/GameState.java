package org.example.csgsi;

public class GameState {
    private MapState        map;
    //private PlayerState     player;
    //private ProviderState   provider;
    //private RoundState      round;
    //private AuthState       auth;

    public GameState() {
    }

    public MapState getMap() {
        return map;
    }

    public void setMap(MapState map) {
        this.map = map;
    }
}
