package org.example.csgsi;

import org.example.utils.json.JsonField;

public class PlayerState {
    @JsonField("steamid")
    private String          steamId;
    @JsonField("name")
    private String          name;
    @JsonField("observer_slot")
    private Integer             observer_slot;
    @JsonField("team")
    private PlayerTeam      team;
    @JsonField("activity")
    private PlayerActivity  activity;
    @JsonField("match_stats")
    private MatchStats      matchStats;
    @JsonField("state")
    private SelfState       selfState;


    public PlayerState(String steamId, String name, Integer observer_slot, PlayerTeam team, PlayerActivity activity, MatchStats matchStats, SelfState selfState) {
        this.steamId = steamId;
        this.name = name;
        this.observer_slot = observer_slot;
        this.team = team;
        this.activity = activity;
        this.matchStats = matchStats;
        this.selfState  = selfState;
    }

    public  enum    PlayerTeam{
        @JsonField("T")
         T,
        @JsonField("CT")
        CT;
    }
    public enum PlayerActivity {
        @JsonField("playing")
        PLAYING,
        @JsonField("menu")
        MENU,
        @JsonField("textinput")
        PROMPT
    }

    public SelfState getSelfState() {
        return selfState;
    }

    public void setSelfState(SelfState selfState) {
        this.selfState = selfState;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getObserver_slot() {
        return observer_slot;
    }

    public void setObserver_slot(Integer observer_slot) {
        this.observer_slot = observer_slot;
    }

    public PlayerTeam getTeam() {
        return team;
    }

    public void setTeam(PlayerTeam team) {
        this.team = team;
    }

    public PlayerActivity getActivity() {
        return activity;
    }

    public void setActivity(PlayerActivity activity) {
        this.activity = activity;
    }

    public MatchStats getMatchStats() {
        return matchStats;
    }

    public void setMatchStats(MatchStats matchStats) {
        this.matchStats = matchStats;
    }
}
