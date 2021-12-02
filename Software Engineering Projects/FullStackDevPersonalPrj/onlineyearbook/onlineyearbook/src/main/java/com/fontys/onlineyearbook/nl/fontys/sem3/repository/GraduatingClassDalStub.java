package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingClassDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;

import java.util.ArrayList;
import java.util.List;

public class GraduatingClassDalStub implements IGraduatingClassDal {

    private List<GraduatingClass>allGradautignClasses;

    public GraduatingClassDalStub(){
        allGradautignClasses = new ArrayList<>();
        allGradautignClasses.add(new GraduatingClass("classA"));
        allGradautignClasses.add(new GraduatingClass("classB"));
        allGradautignClasses.add(new GraduatingClass("classC"));
    }
    @Override
    public GraduatingClass getFirstClassWithClassName(String className) {
        for (GraduatingClass item:allGradautignClasses) {
            if(className.equals(item.getClassName())){
                return item;
            }
        }
        return null;
    }

    @Override
    public GraduatingClass getGraduatingClassByIdFromDB(long id) {
        return null;
    }

    @Override
    public List<GraduatingClass> getAllGraduatingClassesFromDB() {
        return allGradautignClasses;
    }

    @Override
    public void addGraduatignClassToDB(GraduatingClass gradClass) {
        allGradautignClasses.add(gradClass);
    }
}
