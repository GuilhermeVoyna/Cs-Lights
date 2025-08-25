package org.example.context;

import org.example.csgsi.GameState;

public final class ApplicationContext {
    private final GameState gameState;

    public ApplicationContext(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

}
