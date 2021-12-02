package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IYearBookCommitteeMemberDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class YearBookCommitteeMemberDalJPA implements IYearBookCommitteeMemberDal {
    @Autowired
    IYearBookCommitteeMemberRepository repo;

    @Override
    public YearBookCommitteeMember getFirstYBCMWithProfileID(String username) {
        return repo.getFirstYBCMByUsername(username);
    }

    @Override
    public List<YearBookCommitteeMember> getAllYearBookCMsFromDB() {
        return repo.findAll();
    }

    @Override
    public void addyearBookCMToDB(YearBookCommitteeMember ybcm) {
        repo.save(ybcm);
    }
}
