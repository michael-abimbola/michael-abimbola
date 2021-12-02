package com.fontys.onlineyearbook.nl.fontys.sem3.config;

public class AuthenticationConfigConstants {
    private AuthenticationConfigConstants(){

    }
    public static final String SECRET = "Java_to_Dev_Secret";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/admin";
    public static final String ADMIN = "ADMIN";
    public static final String STUDENT = "STUDENT";
    public static final String TEACHER = "TEACHER";
    public static final String YBCM = "YBCM";
    public static final String GUEST = "GUEST";
}
