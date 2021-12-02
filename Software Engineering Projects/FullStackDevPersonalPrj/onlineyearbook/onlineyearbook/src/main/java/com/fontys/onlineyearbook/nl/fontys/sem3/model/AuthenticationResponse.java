package com.fontys.onlineyearbook.nl.fontys.sem3.model;

public class AuthenticationResponse {
    private final String jwt;

    public  AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt(){
        return jwt;
    }
}
