package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class GraduatingClassTest {
    GraduatingClass classA = new GraduatingClass("classA");

    @Test
    void getClassNameTest(){
        String actual = classA.getClassName();

        assertEquals("classA", actual);
    }
}
