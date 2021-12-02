package com.fontys.onlineyearbook.serviceFakedb;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IYearBookCommitteeMemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")

@SpringBootTest
public class YBCMClassServiceFakeTest {
    @Autowired
    IYearBookCommitteeMemberService service;

//    @Test
//    void GetFirstYBCMByUsrIDTest(){
//        //arrange
//        YearBookCommitteeMember y1 = new YearBookCommitteeMember("123456", "John Doe", "JohnDoe@02", "YBCM");
//        YearBookCommitteeMember y2 = new YearBookCommitteeMember("234567", "Johnny Dax", "JohnnyDax@02", "YBCM");
//        YearBookCommitteeMember y3 = new YearBookCommitteeMember("345678", "Jason Dax", "Jason@02", "YBCM");
//        service.addyearBookCM(y1);
//        service.addyearBookCM(y2);
//        service.addyearBookCM(y3);
//
//
//        //act
//        YearBookCommitteeMember actual = service.getFirstYBCMByUsrID("123456");
//
//        //assert
//        assertEquals(y1, actual);
//    }

    @Test
    void GetAllYBCMTest(){
        //arrange
        YearBookCommitteeMember y1 = new YearBookCommitteeMember("123456", "John Doe", "JohnDoe@02", "YBCM");
        YearBookCommitteeMember y2 = new YearBookCommitteeMember("234567", "Johnny Dax", "JohnnyDax@02", "YBCM");
        YearBookCommitteeMember y3 = new YearBookCommitteeMember("345678", "Jason Dax", "Jason@02", "YBCM");
        service.addyearBookCM(y1);
        service.addyearBookCM(y2);
        service.addyearBookCM(y3);


        //act
        List<YearBookCommitteeMember> allYBCM = service.getAllYearBookCMs();

        //assert
        assertNotEquals(null, allYBCM);
    }

    @Test
    void CheckIfYBCMWasAdded(){
        //arrange
        YearBookCommitteeMember y1 = new YearBookCommitteeMember("123456", "John Doe", "JohnDoe@02", "YBCM");
        YearBookCommitteeMember y2 = new YearBookCommitteeMember("234567", "Johnny Dax", "JohnnyDax@02", "YBCM");
        YearBookCommitteeMember y3 = new YearBookCommitteeMember("345678", "Jason Dax", "Jason@02", "YBCM");
        service.addyearBookCM(y1);
        service.addyearBookCM(y2);
        service.addyearBookCM(y3);

        //act
        boolean actual = service.getAllYearBookCMs().contains(y1);

        //assert
        assertEquals(true, actual);
    }

}
