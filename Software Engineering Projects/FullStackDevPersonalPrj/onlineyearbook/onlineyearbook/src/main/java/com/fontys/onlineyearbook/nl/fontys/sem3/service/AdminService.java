package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IAdminDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Admin;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService implements IAdminService {

    IAdminDal dal;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public AdminService(IAdminDal dal, BCryptPasswordEncoder passwordEncoder){
        this.dal= dal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Admin getFirstAdminByUsrID (String usrID){
        return dal.getFirstAdminWithProfileID(usrID);
    }


    @Override
    public List<Admin> getAllAdmin() {
        return dal.getAllAdminFromDB();
    }

    @Override
    public boolean addAdmin(Admin admin) throws NullFieldsException{
        if(admin.getUsername().isEmpty() || admin.getPwd().isEmpty() || admin.getRole().isEmpty() || admin.getProfileName().isEmpty()){
            throw new NullFieldsException("one of the required fields is empty");
        }
        admin.setPwd(passwordEncoder.encode(admin.getPwd()));
        dal.addAdminToDB(admin);
        return true;
    }
}
