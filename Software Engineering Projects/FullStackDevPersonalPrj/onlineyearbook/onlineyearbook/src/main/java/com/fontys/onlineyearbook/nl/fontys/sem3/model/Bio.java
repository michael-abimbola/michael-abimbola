package com.fontys.onlineyearbook.nl.fontys.sem3.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bio")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class Bio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullName;
    private String dateOfBirth;
    private String nickName;
    private int schoolStartingYear;
    private String roleModel;
    private String stateOfOrigin;
    private String hobbies;
    private String favorableQuote;
    private String memorableDay;
    private String farewellMsg;

    public Bio(String fName, String dateOfBirth, String nickName, int ssy, String roleModel, String soo, String hobbies, String favorableQuote,
    String memorableDay, String farewellMsg){
        this.fullName = fName;
        this.dateOfBirth = dateOfBirth;
        this.nickName = nickName;
        this.schoolStartingYear = ssy;
        this.roleModel = roleModel;
        this.stateOfOrigin = soo;
        this.hobbies = hobbies;
        this.favorableQuote = favorableQuote;
        this.memorableDay = memorableDay;
        this.farewellMsg = farewellMsg;
    }
}
