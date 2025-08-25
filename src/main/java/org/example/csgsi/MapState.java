package org.example.csgsi;

public class MapState {

    private         GameMode    gameMode;
    private         String      mapName;
    private         GamePhase   gamePhase;
    private         Number      round;
    private final   TeamState   team_ct;
    private final   TeamState   team_t;

    public MapState(GameMode gameMode, String mapName, GamePhase gamePhase,Number timeouts) {
        this.gameMode   = gameMode;
        this.mapName    = mapName;
        this.gamePhase  = gamePhase;
        this.round      = 0;
        this.team_ct    = new TeamState(0, 0, timeouts);
        this.team_t     = new TeamState(0, 0, timeouts);
    }

    public enum GameMode{
        COMPETITIVE("competitive"),
        CASUAL("casual"),
        DEATHMATCH("deathmatch"),
        WAR_GAMES("skirmish"),
        DANGER_ZONE("survival"),
        WINGMAN_2V2("scrimcomp2v2");

        private final String value;
        GameMode(String value) {
            this.value = value;
        }
        @Override
        public String toString(){
            return value;
        }
    }

    public enum GamePhase{
        WARMUP("warmup")
        ,FREEZE_TIME("freezetime")
        ,LIVE("live")
        ,BOMB("bomb")
        ,DEFUSE("defuse")
        ,OVER("over")
        ,INTERMISSION("intermission")
        ,GAME_OVER("gameover");

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
