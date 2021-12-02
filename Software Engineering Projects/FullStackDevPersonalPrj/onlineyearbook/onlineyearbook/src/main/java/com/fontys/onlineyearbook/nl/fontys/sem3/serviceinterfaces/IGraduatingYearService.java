package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;

import java.util.List;

public interface IGraduatingYearService {
    GraduatingYear getFirstGraduationYearByGradYear (int gradYear);
    GraduatingYear getGraduatingYearById(long id);
    List<GraduatingYear> getAllGraduatingYears();
    String addGraduatingYear(GraduatingYear gradyear);
}
