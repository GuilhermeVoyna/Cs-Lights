package org.example.context;

import org.example.csgsi.GameState;

public class ApplicationContext {
    private GameState gameState;

    public GameState getGameState() {
        return gameState;
    }

    public ApplicationContext(GameState gameState) {
        this.gameState = gameState;
    }
}
