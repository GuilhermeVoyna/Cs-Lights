package org.example.csgsi;

public class MapState {

    private GameMode    gameMode;
    private String      mapName;
    private GamePhase   gamePhase;
    private Number      round;
    private Team        team_ct;
    private Team        team_t;

    public MapState(GameMode gameMode, String mapName, GamePhase gamePhase,Number timeouts) {
        this.gameMode   = gameMode;
        this.mapName    = mapName;
        this.gamePhase  = gamePhase;
        this.round      = 0;
        this.team_ct    = new Team(0,0,timeouts);
        this.team_t     = new Team(0,0,timeouts);
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

    public class Team{
        private Number  score;
        private Number  consecutive_losses;
        private Number  timeouts;

        public Team(Number score, Number consecutive_losses, Number timeouts) {
            this.score = score;
            this.consecutive_losses = consecutive_losses;
            this.timeouts = timeouts;
        }

        public Number getScore() {
            return score;
        }

        public void setScore(Number score) {
            this.score = score;
        }

        public Number getConsecutive_losses() {
            return consecutive_losses;
        }

        public void setConsecutive_losses(Number consecutive_losses) {
            this.consecutive_losses = consecutive_losses;
        }

        public Number getTimeouts() {
            return timeouts;
        }

        public void setTimeouts(Number timeouts) {
            this.timeouts = timeouts;
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

    public Team getTeam_ct() {
        return team_ct;
    }

    public void setTeam_ct(Team team_ct) {
        this.team_ct = team_ct;
    }

    public Team getTeam_t() {
        return team_t;
    }

    public void setTeam_t(Team team_t) {
        this.team_t = team_t;
    }
}
