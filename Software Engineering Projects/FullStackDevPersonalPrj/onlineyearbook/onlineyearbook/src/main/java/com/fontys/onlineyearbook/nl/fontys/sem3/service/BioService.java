package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IBioDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IBioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BioService implements IBioService {
    IBioDal dal;
    @Autowired
    public BioService(IBioDal dal){
        this.dal = dal;
    }
    @Override
    public boolean addStudentBio(Bio studentBio) {
        dal.addBioT0DB(studentBio);
        return true;
    }

    @Override
    public Bio getBioIDByFullName(String fName) {
        return dal.getBioIDByFulNameFromDB(fName);
    }
}
