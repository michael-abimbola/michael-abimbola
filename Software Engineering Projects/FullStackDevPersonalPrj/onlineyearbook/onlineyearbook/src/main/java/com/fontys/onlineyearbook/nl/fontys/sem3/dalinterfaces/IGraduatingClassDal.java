package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;

import java.util.List;

public interface IGraduatingClassDal {
    GraduatingClass getFirstClassWithClassName(String className);
    GraduatingClass getGraduatingClassByIdFromDB(long id);
    List<GraduatingClass> getAllGraduatingClassesFromDB();
    void addGraduatignClassToDB(GraduatingClass gradClass);
}
