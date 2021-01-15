package com.learning.basics.models;

import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private String refreshJwt;

    public AuthenticationResponse(String jwt, String refreshJwt) {
        this.jwt = jwt;
        this.refreshJwt = refreshJwt;
    }
}
