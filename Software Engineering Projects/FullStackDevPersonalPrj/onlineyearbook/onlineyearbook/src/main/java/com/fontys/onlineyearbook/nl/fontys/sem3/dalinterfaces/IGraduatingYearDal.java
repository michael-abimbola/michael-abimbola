package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;

import java.util.List;

public interface IGraduatingYearDal {
    GraduatingYear getFirstGraduatingYearWithGradYear(int gradYear);
    GraduatingYear getGraduatingYearByIdFromDB(long id);
    List<GraduatingYear> getAllGraduatingYearsFromDB();
    void addGraduatingYearToDB(GraduatingYear gradyear);
}
