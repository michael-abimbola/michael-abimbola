package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;

import java.util.List;

public interface IYearBookCommitteeMemberDal {
    YearBookCommitteeMember getFirstYBCMWithProfileID(String username);
    List<YearBookCommitteeMember> getAllYearBookCMsFromDB();
    void addyearBookCMToDB(YearBookCommitteeMember ybcm);
}
