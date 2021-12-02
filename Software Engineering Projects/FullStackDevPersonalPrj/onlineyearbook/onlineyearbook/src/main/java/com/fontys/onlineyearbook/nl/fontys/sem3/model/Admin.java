package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Profile{
    public Admin(String username, String profileName, String pwd, String role) {
        super(username, profileName, pwd, role);
    }

    public Admin() {
    }

}
