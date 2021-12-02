package com.fontys.onlineyearbook.nl.fontys.sem3.controller;

import com.fontys.onlineyearbook.nl.fontys.sem3.DTO.ProfileDTO;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {
    @Autowired
    private IProfileService service;

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles(){
        List<Profile> allProfiles = null;
        allProfiles = service.getAllProfiles();
        if(allProfiles != null){
            return ResponseEntity.ok().body(allProfiles);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/loggedInUser/{username}")
    public ResponseEntity<Profile> getProfile(@PathVariable(value = "username") String username){
        if(service.readUserByUsername(username) != null){
            Profile profile = service.readUserByUsername(username);
            return ResponseEntity.ok().body(profile);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping()
    public ResponseEntity<Profile> updateUserProfile(@RequestParam(value = "username") String username,
                                                     @RequestParam(value = "profileName") String profileName,
                                                     @RequestParam(value = "pwd") String pwd){
        if(service.readUserByUsername(username) != null){
            service.updateProfile(profileName, pwd, username);
            String entity = "Profile updated";
            return new ResponseEntity(entity, HttpStatus.ACCEPTED);
        }
        String entity = "Profile not found";
        return new ResponseEntity(entity, HttpStatus.NOT_FOUND);
    }
}
