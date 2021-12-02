package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.AuthenticationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class AuthenticationResponseTest {
    AuthenticationResponse ar = new AuthenticationResponse("jwttoken");

    @Test
    void getJWTTest(){
        String actual = ar.getJwt();

        assertEquals("jwttoken", actual);
    }

}
