package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IBioDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BioDalJPA implements IBioDal {

    @Autowired
    IBioRepository repo;

    @Override
    public void addBioT0DB(Bio studentBio) {
        repo.save(studentBio);
    }

    @Override
    public Bio getBioIDByFulNameFromDB(String fName) {
        return repo.getBioIDByFullName(fName);
    }
}
