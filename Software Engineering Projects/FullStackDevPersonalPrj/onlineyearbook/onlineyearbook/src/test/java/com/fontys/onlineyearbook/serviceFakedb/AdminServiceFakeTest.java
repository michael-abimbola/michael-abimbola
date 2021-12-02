package com.fontys.onlineyearbook.serviceFakedb;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IAdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")

@SpringBootTest
public class AdminServiceFakeTest {
    @Autowired
    IAdminService service;

//    @Test
//    void GetFirstAdminByUsrIDTest(){
//        //arrange
//        Admin a1 = new Admin("123456", "John Doe", "JohnDoe@02", "ADMIN");
//        Admin a2 = new Admin("234567", "Johnny Dax", "JohnnyDax@02", "ADMIN");
//        Admin a3 = new Admin("345678", "Jason Dax", "Jason@02", "ADMIN");
//        service.addAdmin(a1);
//        service.addAdmin(a2);
//        service.addAdmin(a3);
//
//
//        //act
//        Admin actual = service.getFirstAdminByUsrID("123456");
//
//        //assert
//        assertEquals(a1, actual);
//    }

    @Test
    void GetAllAdminTest(){
        //arrange
        Admin a1 = new Admin("123456", "John Doe", "JohnDoe@02", "ADMIN");
        Admin a2 = new Admin("234567", "Johnny Dax", "JohnnyDax@02", "ADMIN");
        Admin a3 = new Admin("345678", "Jason Dax", "Jason@02", "ADMIN");
        service.addAdmin(a1);
        service.addAdmin(a2);
        service.addAdmin(a3);

        //act
        List<Admin> allAdmins = service.getAllAdmin();

        //assert
        assertNotEquals(null, allAdmins);
    }

    @Test
    void CheckIfAdminWasAdded(){
        //arrange
        Admin a1 = new Admin("123456", "John Doe", "JohnDoe@02", "ADMIN");
        Admin a2 = new Admin("234567", "Johnny Dax", "JohnnyDax@02", "ADMIN");
        Admin a3 = new Admin("345678", "Jason Dax", "Jason@02", "ADMIN");
        service.addAdmin(a1);
        service.addAdmin(a2);
        service.addAdmin(a3);

        //act
        boolean actual = service.getAllAdmin().contains(a1);

        //assert
        assertEquals(true, actual);
    }
}
