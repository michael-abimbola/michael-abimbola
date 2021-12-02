package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "yearbookcommitteemember")
public class YearBookCommitteeMember extends Profile{
    public YearBookCommitteeMember(String username, String profileName, String pwd, String role) {
        super(username, profileName, pwd, role);
    }

    public YearBookCommitteeMember() {
    }


}
