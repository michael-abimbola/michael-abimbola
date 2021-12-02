package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IFarewellMsgDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FarewellMsgDalJPA implements IFarewellMsgDal {

    @Autowired
    IFareWellMsgRepository repo;

    @Override
    public FarewellMessage getFarewellMessageByIdFromDB(long id) {
        return repo.getFarewellMessageById(id);
    }

    @Override
    public void addFarewellMsgToDB(FarewellMessage farewellMessage) {
        repo.save(farewellMessage);
    }

    @Override
    public void deleteFarewellMsgFromDB(long id) {
        repo.deleteFarewellMsg(id);
    }

    @Override
    public void updateFarewellMsgInDB(String msg, long id) {
        repo.updateFarewellMsg(msg, id);
    }

    @Override
    public List<FarewellMessage> getFarewellMessageByGraduatingClassFromDB(GraduatingClass gradClass) {
        List<FarewellMessage> farewellMessages = (List<FarewellMessage>) repo.getFarewellMessageByGraduatingClass(gradClass);
        return farewellMessages;
    }
}
