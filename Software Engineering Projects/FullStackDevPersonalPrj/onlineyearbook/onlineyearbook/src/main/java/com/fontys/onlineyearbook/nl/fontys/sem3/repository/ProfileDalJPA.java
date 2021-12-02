package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IProfileDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileDalJPA implements IProfileDal {
    @Autowired
    IProfileRepository repo;

    @Override
    public List<Profile> getAllProfilesFromDB() {
        return repo.findAll();
    }

    @Override
    public Optional<Profile> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public void updateProfileInDB(String profileName, String pwd, String username) {
        repo.updateProfile(profileName, pwd, username);
    }

}
