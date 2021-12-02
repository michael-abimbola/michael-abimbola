package com.fontys.onlineyearbook.model;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

//this is to tell this test class to work with th test datasource(h2 db)
@ActiveProfiles("test")

@SpringBootTest
class StudentTest {
    GraduatingYear year2018 = new GraduatingYear(2018);
    GraduatingClass classA = new GraduatingClass("classA");
    Student student = new Student("1234567", "Joey", "JoeMains@02","STUDENT", year2018, classA);

    @Test
    void getIDTest(){
        String actual = student.getUsername();

        assertEquals("1234567", actual);
    }

    @Test
    void getNameTest(){
        String actual = student.getProfileName();

        assertEquals("Joey", actual);
    }

    @Test
    void getPasswordTest(){
        String actual = student.getPwd();

        assertEquals("JoeMains@02", actual);
    }

    @Test
    void getRoleTest(){
        String actual = student.getRole();

        assertEquals("STUDENT", actual);
    }

    @Test()
    void getGradYearTest(){
        GraduatingYear actual = student.getGraduatingYear();

        assertEquals(year2018, actual);
    }

    @Test()
    void getGradClassTest(){
        GraduatingClass actual = student.getGraduatingClass();

        assertEquals(classA, actual);
    }
}
