package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;

import java.util.List;

public interface IYearBookCommitteeMemberService {
    YearBookCommitteeMember getFirstYBCMByUsrID (String usrID);
    List<YearBookCommitteeMember> getAllYearBookCMs();
    boolean addyearBookCM(YearBookCommitteeMember ybcm);
}
