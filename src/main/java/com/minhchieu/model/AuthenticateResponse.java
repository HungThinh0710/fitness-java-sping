package com.minhchieu.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
public class AuthenticateResponse implements Serializable {
    private final String token;

    public AuthenticateResponse(String token) {
        this.token = token;
    }
}
