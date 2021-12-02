package com.fontys.onlineyearbook.serviceFakedb;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingYearService;
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
class GraduatingYearServiceFakeTest {
    @Autowired
    IGraduatingYearService service;

    @Test
    void GetFirstGraduationYearByGradYearTes(){
        //arrange
        GraduatingYear g1 = new GraduatingYear(2000);
        GraduatingYear g2 = new GraduatingYear(2001);
        GraduatingYear g3 = new GraduatingYear(2002);
        service.addGraduatingYear(g1);
        service.addGraduatingYear(g2);
        service.addGraduatingYear(g3);

        //act
        GraduatingYear actual = service.getFirstGraduationYearByGradYear(2000);

        //assert
        assertEquals(g1, actual);
    }

    @Test
    void GetAllGradYearsTest(){
        //arrange
        GraduatingYear g1 = new GraduatingYear(2000);
        GraduatingYear g2 = new GraduatingYear(2001);
        GraduatingYear g3 = new GraduatingYear(2002);
        service.addGraduatingYear(g1);
        service.addGraduatingYear(g2);
        service.addGraduatingYear(g3);

        //act
        List<GraduatingYear> allGradYears = service.getAllGraduatingYears();

        //assert
        assertNotEquals(null, allGradYears);
    }

    @Test
    void CheckIfAddedGradYearWasAddedTest(){
        //arrange
        GraduatingYear g1 = new GraduatingYear(2000);
        GraduatingYear g2 = new GraduatingYear(2001);
        GraduatingYear g3 = new GraduatingYear(2002);
        service.addGraduatingYear(g1);
        service.addGraduatingYear(g2);
        service.addGraduatingYear(g3);

        //act
        boolean actual = service.getAllGraduatingYears().contains(g1);

        //assert
        assertEquals(true, actual);
    }



}
