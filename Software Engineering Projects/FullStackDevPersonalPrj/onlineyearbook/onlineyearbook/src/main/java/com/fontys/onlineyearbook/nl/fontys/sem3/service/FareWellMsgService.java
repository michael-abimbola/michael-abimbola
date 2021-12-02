package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IFarewellMsgDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IFareWellMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FareWellMsgService implements IFareWellMsgService {
    IFarewellMsgDal dal;

    @Autowired
    public FareWellMsgService(IFarewellMsgDal dal){
        this.dal = dal;
    }

    @Override
    public FarewellMessage getFarewellMsgById(long id) {
        return dal.getFarewellMessageByIdFromDB(id);
    }

    @Override
    public boolean addFarewellMsg(FarewellMessage farewellMessage) {
        dal.addFarewellMsgToDB(farewellMessage);
        return true;
    }

    @Override
    public boolean deleteFarewellMsg(long id) {
        dal.deleteFarewellMsgFromDB(id);
        return true;
    }

    @Override
    public boolean updateFarewellMsg(String msg, long id) {
        dal.updateFarewellMsgInDB(msg, id);
        return true;
    }

    @Override
    public List<FarewellMessage> getFarewellMessageByGradClass(GraduatingClass gradClass) {
        return dal.getFarewellMessageByGraduatingClassFromDB(gradClass);
    }
}
