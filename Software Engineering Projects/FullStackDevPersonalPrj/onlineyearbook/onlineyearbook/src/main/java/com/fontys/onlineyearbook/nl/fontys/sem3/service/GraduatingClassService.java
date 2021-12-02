package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingClassDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduatingClassService implements IGraduatingClassService {
    IGraduatingClassDal dal;
    @Autowired
    public GraduatingClassService(IGraduatingClassDal dal){
        this.dal= dal;
    }

    @Override
    public GraduatingClass getFirstGraduatingClassByClassName (String gradClass){
        return dal.getFirstClassWithClassName(gradClass);
    }

    @Override
    public GraduatingClass getGraduatingClassById(long id) {
        return dal.getGraduatingClassByIdFromDB(id);
    }

    @Override
    public List<GraduatingClass> getAllGraduatingClasses() {
        return dal.getAllGraduatingClassesFromDB();
    }

    @Override
    public String addGraduatignClass(GraduatingClass gradClass) {
        if(getFirstGraduatingClassByClassName(gradClass.getClassName())!=null){
            return "exists";
        }
        dal.addGraduatignClassToDB(gradClass);
        return "added";
    }
}
