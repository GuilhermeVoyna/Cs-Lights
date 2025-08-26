package org.example.csgsi;

public class PlayerSate {
    private String          steamid;
    private PlayerTeam      team;
    private PlayerActivity  activity;
    private MatchStats      matchStats;

    public  enum    PlayerTeam{
         T
        ,CT;
    }
    public enum PlayerActivity {

    }

    public  class    MatchStats{
        public Number kills;
        public Number assists;
        public Number deaths;
        public Number mvps;
        public Number score;

        public MatchStats(Number kills, Number assists, Number deaths, Number mvps, Number score) {
            this.kills = kills;
            this.assists = assists;
            this.deaths = deaths;
            this.mvps = mvps;
            this.score = score;
        }
    }
}
