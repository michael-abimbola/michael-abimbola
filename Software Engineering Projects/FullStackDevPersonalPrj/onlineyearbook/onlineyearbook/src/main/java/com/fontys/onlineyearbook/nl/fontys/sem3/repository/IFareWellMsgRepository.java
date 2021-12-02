package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.FarewellMessage;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IFareWellMsgRepository extends JpaRepository<FarewellMessage, Long> {
    FarewellMessage getFarewellMessageById(long id);

    @Modifying
    @Transactional
    @Query("UPDATE FarewellMessage fm SET fm.msg = ?1 WHERE fm.id = ?2" )
    void updateFarewellMsg(String msg, long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM FarewellMessage fm WHERE fm.id = ?1")
    void deleteFarewellMsg(long id);

    @Query("SELECT fm FROM FarewellMessage AS fm WHERE fm.graduatingClass = ?1")
    List<FarewellMessage> getFarewellMessageByGraduatingClass(GraduatingClass gradClass);
}
