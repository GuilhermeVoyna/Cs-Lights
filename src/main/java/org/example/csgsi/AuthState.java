package org.example.csgsi;

import org.example.utils.json.JsonField;

public class AuthState {
    @JsonField("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthState(String token) {
        this.token = token;
    }
}
