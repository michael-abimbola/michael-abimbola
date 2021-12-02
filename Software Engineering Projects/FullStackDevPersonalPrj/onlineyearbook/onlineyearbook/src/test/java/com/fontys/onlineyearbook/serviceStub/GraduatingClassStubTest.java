package com.fontys.onlineyearbook.serviceStub;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.repository.GraduatingClassDalStub;
import com.fontys.onlineyearbook.nl.fontys.sem3.service.GraduatingClassService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraduatingClassStubTest {
    GraduatingClassService service;

    @Test
    void checkIfGradClassExists(){
        //arrange
        GraduatingClass c1 = new GraduatingClass("classA");
        service = new GraduatingClassService(new GraduatingClassDalStub());

        //act
        GraduatingClass actual = service.getFirstGraduatingClassByClassName("classA");

        //assert
        assertEquals(c1, actual);

    }
}
