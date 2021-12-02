package com.fontys.onlineyearbook.nl.fontys.sem3.repository;


import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingYearDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GraduatingYearDalDalJPA implements IGraduatingYearDal {

    @Autowired
    IGraduatingYearRepository repo;

    @Override
    public GraduatingYear getFirstGraduatingYearWithGradYear(int gradYear) {
        return repo.getFirstByGraduatingYear(gradYear);
    }

    @Override
    public GraduatingYear getGraduatingYearByIdFromDB(long id) {
        return repo.getGraduatingYearById(id);
    }

    @Override
    public List<GraduatingYear> getAllGraduatingYearsFromDB() {
        return repo.findAll();
    }

    @Override
    public void addGraduatingYearToDB(GraduatingYear gradyear) {
        repo.save(gradyear);
    }
}
