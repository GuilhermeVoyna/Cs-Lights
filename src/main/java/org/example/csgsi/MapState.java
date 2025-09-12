package org.example.csgsi;

import org.example.utils.json.JsonField;

public class MapState {
    @JsonField("mode")
    private         GameMode    gameMode;
    @JsonField("name")
    private         String      mapName;
    @JsonField("phase")
    private         GamePhase   gamePhase;
    @JsonField("round")
    private         Number      round;
    @JsonField("team_ct")
    private         TeamState   team_ct;
    @JsonField("team_t")
    private         TeamState   team_t;

    public MapState(GameMode gameMode, String mapName, GamePhase gamePhase, Number round, TeamState team_ct, TeamState team_t) {
        this.gameMode = gameMode;
        this.mapName = mapName;
        this.gamePhase = gamePhase;
        this.round = round;
        this.team_ct = team_ct;
        this.team_t = team_t;
    }

    public enum GameMode{
        @JsonField("competitive")
        COMPETITIVE,
        @JsonField("casual")
        CASUAL,
        @JsonField("deathmatch")
        DEATHMATCH,
        @JsonField("skirmish")
        WAR_GAMES,
        @JsonField("survival")
        DANGER_ZONE,
        @JsonField("scrimcomp2v2")
        WINGMAN_2V2;
    }

    public enum GamePhase{
        @JsonField("warmup")
        WARMUP,
        @JsonField("freezetime")
        FREEZE_TIME,
        @JsonField("live")
        LIVE,
        @JsonField("bom")
        BOMB,
        @JsonField("defuse")
        DEFUSE,
        @JsonField("over")
        OVER,
        @JsonField("intermission")
        INTERMISSION,
        @JsonField("gameover")
        GAME_OVER,

    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public GamePhase getGamePhase() {
        return gamePhase;
    }

    public void setGamePhase(GamePhase gamePhase) {
        this.gamePhase = gamePhase;
    }

    public Number getRound() {
        return round;
    }

    public void setRound(Number round) {
        this.round = round;
    }

    public TeamState getTeam_ct() {
        return team_ct;
    }

    public TeamState getTeam_t() {
        return team_t;
    }
}
