package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;

import java.util.List;

public interface IFarewellMsgDal {
    FarewellMessage getFarewellMessageByIdFromDB(long id);

    void addFarewellMsgToDB(FarewellMessage farewellMessage);

    void deleteFarewellMsgFromDB(long id);

    void updateFarewellMsgInDB(String msg, long id);

    List<FarewellMessage>getFarewellMessageByGraduatingClassFromDB(GraduatingClass gradClass);
}
