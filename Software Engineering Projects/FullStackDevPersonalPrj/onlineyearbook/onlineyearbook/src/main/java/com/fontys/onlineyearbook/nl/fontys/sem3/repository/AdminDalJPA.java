package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IAdminDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDalJPA implements IAdminDal {
    @Autowired
    IAdminRepository repo;

    @Override
    public Admin getFirstAdminWithProfileID(String username) {
        return repo.getFirstByUsername(username);
    }

    @Override
    public List<Admin> getAllAdminFromDB() {
        return repo.findAll();
    }

    @Override
    public void addAdminToDB(Admin admin) {
        repo.save(admin);
    }
}
