package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;

import java.util.List;

public interface IGraduatingClassService {
    GraduatingClass getFirstGraduatingClassByClassName(String gradClass);
    GraduatingClass getGraduatingClassById(long id);
    List<GraduatingClass> getAllGraduatingClasses();
    String addGraduatignClass(GraduatingClass gradClass);
}
