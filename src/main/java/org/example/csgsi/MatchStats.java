package org.example.csgsi;

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