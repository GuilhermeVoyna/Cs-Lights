package org.example.context;

import com.sun.net.httpserver.HttpExchange;
import org.example.csgsi.*;
import org.example.server.AttemptInfo;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    private static final int MAX_ATTEMPTS = 10;
    public static final int BLOCKED_TIME = 10_000;
    private final GameState gameState;
    private final Map<InetSocketAddress, AttemptInfo> attemptMap = new ConcurrentHashMap<InetSocketAddress, AttemptInfo>(); ;


    public ApplicationContext(String token){
        this.gameState = generateNullGameState(token);
    }
    public ApplicationContext(){
        this.gameState = generateNullGameState("");
    }

    public boolean isAttemptBlocked(HttpExchange exchange) {

        InetSocketAddress address = exchange.getLocalAddress();
        AttemptInfo info =  attemptMap.computeIfAbsent(address, k -> new AttemptInfo());
        if (info.getAttempts() >= MAX_ATTEMPTS) {
            long now = System.currentTimeMillis();
            if (now - info.getLastAttemptTime() < BLOCKED_TIME) {
                // The user is still blocked
                return true;
            } else {
                // the user already waited the blocked time
                info.setAttempts(0);
            }
        }
        return false;
    }

    public void registerFailedAttempt(HttpExchange exchange){
        InetSocketAddress address = exchange.getLocalAddress();
        AttemptInfo info = attemptMap.computeIfAbsent(address, k -> new AttemptInfo());
        info.incrementAttempts();
        long now = System.currentTimeMillis();
        info.setLastAttemptTime(now);
    }


    private GameState generateNullGameState(String token) {
        TeamState emptyTeam = new TeamState(null, null, null, null);
        SelfState selfState = new SelfState(-1, -1, null, -1, -1, -1, -1, -1, -1, -1);

        MapState mapState = new MapState(null, null, null, null, emptyTeam, emptyTeam);
        PlayerState playerState = new PlayerState(null, null, -1, null, null, null,selfState);
        ProviderState providerState = new ProviderState(null, -1, null, null);
        RoundState roundState = new RoundState(null, null, null);
        AuthState authState = new AuthState(token);

        return new GameState(mapState, playerState, providerState, roundState, authState);
    }


    public GameState getGameState() {
        return this.gameState;
    }
    public String getToken(){
        return getGameState().getAuthState().getToken();
    }

}
