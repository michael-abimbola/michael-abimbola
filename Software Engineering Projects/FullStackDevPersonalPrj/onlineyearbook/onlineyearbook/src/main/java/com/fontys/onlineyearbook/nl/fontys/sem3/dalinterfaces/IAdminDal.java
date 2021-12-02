package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;

import java.util.List;

public interface IAdminDal {
    Admin getFirstAdminWithProfileID(String username);
    List<Admin> getAllAdminFromDB();
    void addAdminToDB(Admin admin);
}
