package org.example.csgsi;

import org.example.utils.json.JsonField;

public class RoundState {

    @JsonField("phase")
    private RoundPhase roundPhase;
    @JsonField("win_team")
    private WiningTeam winingTeam;
    @JsonField("bomb")
    private BombState  bombState;

    public RoundState(RoundPhase roundPhase, WiningTeam winingTeam, BombState bombState) {
        this.roundPhase = roundPhase;
        this.winingTeam = winingTeam;
        this.bombState = bombState;
    }

    public RoundPhase getRoundPhase() {
        return roundPhase;
    }

    public void setRoundPhase(RoundPhase roundPhase) {
        this.roundPhase = roundPhase;
    }

    public WiningTeam getWiningTeam() {
        return winingTeam;
    }

    public void setWiningTeam(WiningTeam winingTeam) {
        this.winingTeam = winingTeam;
    }

    public BombState getBombState() {
        return bombState;
    }

    public void setBombState(BombState bombState) {
        this.bombState = bombState;
    }

    public enum RoundPhase {
        @JsonField("live")
        LIVE,
        @JsonField("freezetime")
        FREEZE_TIME,
        @JsonField("over")
        OVER
    }
    public  enum    WiningTeam{
        @JsonField("T")
        T,
        @JsonField("CT")
        CT;
    }

    public  enum  BombState {
        @JsonField("planted")
        PLANTED,
        @JsonField("exploded")
        EXPLODED,
        @JsonField("defused")
        DEFUSED
    }


}
