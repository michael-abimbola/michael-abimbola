package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class AdminTest {
    Admin admin = new Admin("1234567", "Joey", "JoeMains@02","ADMIN");

    @Test
    void getUsrIDTest(){
        String actual = admin.getUsername();

        assertEquals("1234567", actual);
    }

    @Test
    void getNameTest(){
        String actual = admin.getProfileName();

        assertEquals("Joey", actual);
    }

    @Test
    void getPasswordTest(){
        String actual = admin.getPwd();

        assertEquals("JoeMains@02", actual);
    }

    @Test
    void getRoleTest(){
        String actual = admin.getRole();

        assertEquals("ADMIN", actual);
    }
}
