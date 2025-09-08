package org.example.csgsi;

import org.example.utils.json.JsonField;

public  class    MatchStats{
    @JsonField("kills")
    public Number kills;
    @JsonField("assists")
    public Number assists;
    @JsonField("deaths")
    public Number deaths;
    @JsonField("mvps")
    public Number mvps;
    @JsonField("score")
    public Number score;

    public MatchStats(Number kills, Number assists, Number deaths, Number mvps, Number score) {
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.mvps = mvps;
        this.score = score;
    }
}