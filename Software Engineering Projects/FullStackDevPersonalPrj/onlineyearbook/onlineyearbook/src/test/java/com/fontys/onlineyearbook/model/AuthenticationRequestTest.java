package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.AuthenticationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class AuthenticationRequestTest {
    AuthenticationRequest ar = new AuthenticationRequest("1234567", "Mikoko@06");

    @Test
    void getUsernameTest(){
        String actual = ar.getUsername();

        assertEquals("1234567", actual);
    }

    @Test
    void getPasswordTest(){
        String actual = ar.getPassword();

        assertEquals("Mikoko@06", actual);
    }
}
