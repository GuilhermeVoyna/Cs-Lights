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
    private    TeamState   team_ct;
    @JsonField("team_t")
    private    TeamState   team_t;

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
        WARMUP("warmup")
        ,FREEZE_TIME("freezetime")
        ,LIVE("live")
        ,BOMB("bomb")
        ,DEFUSE("defuse")
        ,OVER("over")
        ,INTERMISSION("intermission")
        ,GAME_OVER("gameover"),
        warmup("json"),
        freezetime("json"),
        live("json"),
        bomb("json"),
        defuse("json"),
        over("json"),
        intermission("json"),
        gameover("json");

        private final  String value;
        GamePhase(String value){
            this.value = value;
            }
        @Override
        public String toString(){
            return value;
            }
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
