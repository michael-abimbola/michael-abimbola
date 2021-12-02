package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IGraduatingYearDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IGraduatingYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduatingYearService implements IGraduatingYearService {
    IGraduatingYearDal dal;
    @Autowired
    public GraduatingYearService(IGraduatingYearDal dal){
        this.dal = dal;
    }

    @Override
    public GraduatingYear getFirstGraduationYearByGradYear (int gradYear){
        return dal.getFirstGraduatingYearWithGradYear(gradYear);
    }

    @Override
    public GraduatingYear getGraduatingYearById(long id) {
        return dal.getGraduatingYearByIdFromDB(id);
    }

    @Override
    public List<GraduatingYear> getAllGraduatingYears() {
        return dal.getAllGraduatingYearsFromDB();
    }

    @Override
    public String addGraduatingYear(GraduatingYear gradyear) {
        if(gradyear.getGraduatingYear() == 0){
            return "zero input";
        }
        else if (getFirstGraduationYearByGradYear(gradyear.getGraduatingYear())!= null){
            return "exists";
        }
        dal.addGraduatingYearToDB(gradyear);
        return "added";
    }
}
