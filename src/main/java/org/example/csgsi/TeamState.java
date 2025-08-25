package org.example.csgsi;

public class TeamState {
    private Number  score;
    private Number  consecutive_losses;
    private Number  timeouts;

    public TeamState(Number score, Number consecutive_losses, Number timeouts) {
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


