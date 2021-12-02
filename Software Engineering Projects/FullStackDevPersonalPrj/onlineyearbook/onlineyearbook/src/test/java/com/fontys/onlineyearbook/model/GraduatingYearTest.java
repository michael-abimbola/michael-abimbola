package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class GraduatingYearTest {
    GraduatingYear year2018 = new GraduatingYear(2018);

    @Test()
    void getGradYearTest(){
        int actual = year2018.getGraduatingYear();

        assertEquals(2018, actual);
    }
}
