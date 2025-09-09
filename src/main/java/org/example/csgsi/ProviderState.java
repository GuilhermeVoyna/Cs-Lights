package org.example.csgsi;

import org.example.utils.json.JsonField;

public class ProviderState {
    @JsonField("name")
    private String name;
    @JsonField("appid")
    private int appId;
    @JsonField("steamid")
    private String steamId;
    @JsonField("timestamp")
    private Integer timestamp;

    public ProviderState(String name, int appId, String steamId, Integer timestamp) {
        this.name = name;
        this.appId = appId;
        this.steamId = steamId;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public String getSteamId() {
        return steamId;
    }

    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
