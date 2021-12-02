package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.config.AuthenticationConfigConstants;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;

@Service
public class AuthenticationUserDetailService implements UserDetailsService {

    @Autowired
    private IProfileService profileService;

    //this method expects you to load a user form wherever you saved the user
    //in my case a database and with that i use the profile service
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals(AuthenticationConfigConstants.GUEST)){
            return new org.springframework.security.core.userdetails.User(AuthenticationConfigConstants.GUEST, AuthenticationConfigConstants.GUEST, getAuthorities(AuthenticationConfigConstants.GUEST));
        }
        Profile profile = profileService.readUserByUsername(username);
        if(profile == null){
            throw new UsernameNotFoundException(username);
        }
        //what ever password i have entered will be compared ot the password of the found username
        return new org.springframework.security.core.userdetails.User(profile.getUsername(), profile.getPwd(), getAuthorities(profile.getRole()));

    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
