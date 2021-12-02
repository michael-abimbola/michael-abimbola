package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IYearBookCommitteeMemberDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IYearBookCommitteeMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearBookCommitteeMemberService implements IYearBookCommitteeMemberService {
    IYearBookCommitteeMemberDal dal;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public YearBookCommitteeMemberService(IYearBookCommitteeMemberDal dal, BCryptPasswordEncoder passwordEncoder){
        this.dal = dal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public YearBookCommitteeMember getFirstYBCMByUsrID (String usrID){
        return dal.getFirstYBCMWithProfileID(usrID);
    }

    @Override
    public List<YearBookCommitteeMember> getAllYearBookCMs() {
        return dal.getAllYearBookCMsFromDB();
    }

    @Override
    public boolean addyearBookCM(YearBookCommitteeMember ybcm) {
        if(ybcm.getUsername().isEmpty() || ybcm.getPwd().isEmpty() || ybcm.getRole().isEmpty() || ybcm.getProfileName().isEmpty()){
            throw new NullFieldsException("one of the required fields is empty");
        }
        ybcm.setPwd(passwordEncoder.encode(ybcm.getPwd()));
        dal.addyearBookCMToDB(ybcm);
        return true;
    }
}
