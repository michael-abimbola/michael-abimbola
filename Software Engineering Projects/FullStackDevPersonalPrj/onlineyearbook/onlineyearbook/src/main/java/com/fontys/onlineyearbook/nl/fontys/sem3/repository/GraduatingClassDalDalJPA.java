package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingClassDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GraduatingClassDalDalJPA implements IGraduatingClassDal {

    @Autowired
    IGraduatingClassRepository repo;

    @Override
    public GraduatingClass getFirstClassWithClassName(String className) {
        return repo.getFirstByClassName(className);
    }

    @Override
    public GraduatingClass getGraduatingClassByIdFromDB(long id) {
        return repo.getGraduatingClassById(id);
    }

    @Override
    public List<GraduatingClass> getAllGraduatingClassesFromDB() {
        return repo.findAll();
    }

    @Override
    public void addGraduatignClassToDB(GraduatingClass gradClass) {
        repo.save(gradClass);
    }
}
