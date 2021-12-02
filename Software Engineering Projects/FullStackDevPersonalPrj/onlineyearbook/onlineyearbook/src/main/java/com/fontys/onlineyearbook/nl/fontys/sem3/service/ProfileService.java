package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IProfileDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class ProfileService implements IProfileService {
    IProfileDal dal;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(IProfileDal dal, BCryptPasswordEncoder passwordEncoder){
        this.dal = dal;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<Profile> getAllProfiles() {
        return dal.getAllProfilesFromDB();
    }

    @Override
    public Profile readUserByUsername(String username) {
        return dal.findByUsername(username).orElseThrow(EntityExistsException::new);
    }

    @Override
    public void updateProfile(String profileName, String pwd, String usr) {
        dal.updateProfileInDB(profileName, passwordEncoder.encode(pwd), usr);
    }


}
