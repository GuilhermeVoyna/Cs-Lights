package org.example.csgsi;

public class GameState {
    private MapState map;
    private PlayerState player;
    private ProviderState provider;
    private RoundState round;
    private AuthState auth;

    public GameState(MapState map, PlayerState player, ProviderState provider, RoundState round, AuthState auth) {
        this.map = map;
        this.player = player;
        this.provider = provider;
        this.round = round;
        this.auth = auth;
    }
}
