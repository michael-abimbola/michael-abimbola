package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingYearDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;

import java.util.ArrayList;
import java.util.List;

public class GraduatingYearDalStub implements IGraduatingYearDal {

    private List<GraduatingYear>allGraduatingYears;
    public GraduatingYearDalStub(){
        allGraduatingYears = new ArrayList<>();
        allGraduatingYears.add(new GraduatingYear(2000));
        allGraduatingYears.add(new GraduatingYear(2002));
        allGraduatingYears.add(new GraduatingYear(2003));
    }

    @Override
    public GraduatingYear getFirstGraduatingYearWithGradYear(int gradYear) {
        for (GraduatingYear item:allGraduatingYears) {
            if(item.getGraduatingYear() == gradYear){
                return item;
            }
        }
        return null;
    }

    @Override
    public GraduatingYear getGraduatingYearByIdFromDB(long id) {
        return null;
    }

    @Override
    public List<GraduatingYear> getAllGraduatingYearsFromDB() {
        return allGraduatingYears;
    }

    @Override
    public void addGraduatingYearToDB(GraduatingYear gradyear) {
        allGraduatingYears.add(gradyear);
    }
}
