package com.minhchieu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequest {
    private String email;
    private String password;

    public AuthenticateRequest(){}

    public AuthenticateRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
