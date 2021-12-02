package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;

import java.util.List;

public interface IFareWellMsgService {
    FarewellMessage getFarewellMsgById(long id);

    boolean addFarewellMsg(FarewellMessage farewellMessage);

    boolean deleteFarewellMsg(long id);

    boolean updateFarewellMsg(String msg, long id);

    List<FarewellMessage>getFarewellMessageByGradClass(GraduatingClass gradClass);
}
