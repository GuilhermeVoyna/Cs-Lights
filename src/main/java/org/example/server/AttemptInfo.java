package org.example.server;

public class AttemptInfo {
    private int attempts;
    private long lastAttemptTime;

    public AttemptInfo() {
        this.attempts = 0;
        this.lastAttemptTime = System.currentTimeMillis();
    }

    public void incrementAttempts() {
        this.attempts++;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public long getLastAttemptTime() {
        return lastAttemptTime;
    }

    public void setLastAttemptTime(long lastAttemptTime) {
        this.lastAttemptTime = lastAttemptTime;
    }

    public int getAttempts() {
        return attempts;
    }
}
