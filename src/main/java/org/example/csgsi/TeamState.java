package org.example.csgsi;

import org.example.utils.json.JsonField;

public class TeamState {
    @JsonField("score")
    private Number  score;
    @JsonField("consecutive_round_losses")
    private Number  consecutive_losses;
    @JsonField("timeouts_remaining")
    private Number  timeouts;
    @JsonField("matches_won_this_series")
    private Number  matches_won;

    public Number getMatches_won() {
        return matches_won;
    }

    public void setMatches_won(Number matches_won) {
        this.matches_won = matches_won;
    }

    public TeamState(Number score, Number consecutive_losses, Number timeouts, Number matches_won) {
        this.score = score;
        this.consecutive_losses = consecutive_losses;
        this.timeouts = timeouts;
        this.matches_won = matches_won;
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


