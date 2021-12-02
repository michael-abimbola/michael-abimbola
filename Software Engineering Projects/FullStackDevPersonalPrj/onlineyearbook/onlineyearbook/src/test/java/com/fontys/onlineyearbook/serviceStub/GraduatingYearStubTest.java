package com.fontys.onlineyearbook.serviceStub;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.repository.GraduatingYearDalStub;
import com.fontys.onlineyearbook.nl.fontys.sem3.service.GraduatingYearService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraduatingYearStubTest {
    GraduatingYearService service;

    @Test
    void checkIfGradYearExistsTest(){
        //arrange
        GraduatingYear g1 = new GraduatingYear(2000);
        service = new GraduatingYearService(new GraduatingYearDalStub());

        //act
        GraduatingYear actual = service.getFirstGraduationYearByGradYear(2000);

        //assert
        assertEquals(g1, actual);
    }
}
