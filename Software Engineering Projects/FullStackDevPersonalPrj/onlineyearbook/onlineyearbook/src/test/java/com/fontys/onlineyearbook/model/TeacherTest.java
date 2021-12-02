package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")

@SpringBootTest
public class TeacherTest {
    GraduatingClass classA = new GraduatingClass("classA");
    Teacher teacher = new Teacher("1234567", "Joey", "JoeMains@02","TEACHER",  classA);

    @Test
    void getIDTest(){
        String actual = teacher.getUsername();

        assertEquals("1234567", actual);
    }

    @Test
    void getNameTest(){
        String actual = teacher.getProfileName();

        assertEquals("Joey", actual);
    }

    @Test
    void getPasswordTest(){
        String actual = teacher.getPwd();

        assertEquals("JoeMains@02", actual);
    }

    @Test
    void getRoleTest(){
        String actual = teacher.getRole();

        assertEquals("TEACHER", actual);
    }

    @Test()
    void getGradClassTest(){
        GraduatingClass actual = teacher.getGraduatingClass();

        assertEquals(classA, actual);
    }
}
