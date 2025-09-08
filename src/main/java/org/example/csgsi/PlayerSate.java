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
        PLAYING
        ,MENU
        ,TEXTINPUT
    }

    public PlayerSate(String steamid, PlayerTeam team, PlayerActivity activity, MatchStats matchStats) {
        this.steamid = steamid;
        this.team = team;
        this.activity = activity;
        this.matchStats = matchStats;
    }
}
