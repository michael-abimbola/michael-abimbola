package com.fontys.onlineyearbook.serviceFakedb;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingClassService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

//this is to tell this test class to work with th test datasource(h2 db)
@ActiveProfiles("test")

@SpringBootTest
class GraduatingClassServiceFakeTest {
    @Autowired
    IGraduatingClassService service;

    @Test
    void GetFirstGraduatingClassByClassNameTest(){
        //arrange
        GraduatingClass c1 = new GraduatingClass("classA");
        GraduatingClass c2 = new GraduatingClass("classB");
        GraduatingClass c3 = new GraduatingClass("classC");
        service.addGraduatignClass(c1);
        service.addGraduatignClass(c2);
        service.addGraduatignClass(c3);

        //act
        GraduatingClass actual = service.getFirstGraduatingClassByClassName("classA");

        //assert
        assertEquals(c1.getClassName(), actual.getClassName());
    }

    @Test
    void GetAllGradYearsTest(){
        //arrange
        GraduatingClass c1 = new GraduatingClass("classA");
        GraduatingClass c2 = new GraduatingClass("classB");
        GraduatingClass c3 = new GraduatingClass("classC");
        service.addGraduatignClass(c1);
        service.addGraduatignClass(c2);
        service.addGraduatignClass(c3);

        //act
        List<GraduatingClass> allGradClasses = service.getAllGraduatingClasses();

        //assert
        assertNotEquals(null, allGradClasses);
    }


    @Test
     void CheckIfAddedGradClassWasAddedTest(){
        //arrange
        GraduatingClass c1 = new GraduatingClass("classA");
        GraduatingClass c2 = new GraduatingClass("classB");
        GraduatingClass c3 = new GraduatingClass("classC");
        service.addGraduatignClass(c1);
        service.addGraduatignClass(c2);
        service.addGraduatignClass(c3);

        //act
        boolean actual = service.getAllGraduatingClasses().contains(c1);

        //assert
        assertEquals(true, actual);
    }
}
