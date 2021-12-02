package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;

import java.util.List;

public interface IProfileService {
    List<Profile> getAllProfiles();
    Profile readUserByUsername(String username);
    void updateProfile(String profileName, String pwd, String usr);
}
