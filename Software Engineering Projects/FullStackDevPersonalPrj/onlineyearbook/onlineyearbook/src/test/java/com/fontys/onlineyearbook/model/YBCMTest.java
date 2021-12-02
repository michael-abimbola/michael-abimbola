package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class YBCMTest {
    YearBookCommitteeMember ybcm = new YearBookCommitteeMember("1234567", "Joey", "JoeMains@02","YBCM");

    @Test
    void getUsrIDTest(){
        String actual = ybcm.getUsername();

        assertEquals("1234567", actual);
    }

    @Test
    void getNameTest(){
        String actual = ybcm.getProfileName();

        assertEquals("Joey", actual);
    }

    @Test
    void getPasswordTest(){
        String actual = ybcm.getPwd();

        assertEquals("JoeMains@02", actual);
    }

    @Test
    void getRoleTest(){
        String actual = ybcm.getRole();

        assertEquals("YBCM", actual);
    }
}
