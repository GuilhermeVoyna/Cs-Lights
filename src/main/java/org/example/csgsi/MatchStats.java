package org.example.csgsi;

import org.example.utils.json.JsonField;

public  class    MatchStats{
    @JsonField("kills")
    public Integer kills;
    @JsonField("assists")
    public Integer assists;
    @JsonField("deaths")
    public Integer deaths;
    @JsonField("mvps")
    public Integer mvps;
    @JsonField("score")
    public Integer score;

    public MatchStats(Integer kills, Integer assists, Integer deaths, Integer mvps, Integer score) {
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.mvps = mvps;
        this.score = score;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getMvps() {
        return mvps;
    }

    public void setMvps(Integer mvps) {
        this.mvps = mvps;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}