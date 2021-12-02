package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    protected String username;
    protected String profileName;
    protected String pwd;
    protected String role;

    public Profile(String username, String profileName, String pwd, String role) {
        this.username = username;
        this.profileName = profileName;
        this.pwd = pwd;
        this.role = role;
    }

    public Profile(String username, String profileName, String pwd){
        this.username = username;
        this.profileName = profileName;
        this.pwd = pwd;
    }


    public Profile() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile)) return false;
        Profile profile = (Profile) o;
        return username == profile.username && Objects.equals(profileName, profile.profileName) && Objects.equals(pwd, profile.pwd) && Objects.equals(role, profile.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, profileName, pwd, role);
    }
}
