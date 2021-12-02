package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temporaryAdmin")
@CrossOrigin("*")
public class TemporaryAdminContoller {
    @Autowired
    private IAdminService service;

    @GetMapping("/setup")
    public void addTemporaryAdmin(){
        Admin admin = new Admin("1111111", "admin", "ADMIN", "ADMIN");
        service.addAdmin(admin);
    }
}
