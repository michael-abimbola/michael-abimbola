package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;

import java.util.List;

public interface IAdminService {
    Admin getFirstAdminByUsrID (String usrID);
    List<Admin> getAllAdmin();
    boolean addAdmin(Admin admin);
}
