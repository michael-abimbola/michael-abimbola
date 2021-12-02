package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;

import java.util.List;
import java.util.Optional;

public interface IProfileDal {
    List<Profile>getAllProfilesFromDB();
    Optional<Profile>findByUsername(String username);
    void updateProfileInDB(String profileName, String pwd, String username);
}
