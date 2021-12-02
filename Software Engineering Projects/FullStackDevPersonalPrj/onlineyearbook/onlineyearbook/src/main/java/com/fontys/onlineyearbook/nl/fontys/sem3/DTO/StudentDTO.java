package com.fontys.onlineyearbook.nl.fontys.sem3.DTO;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class StudentDTO extends Profile {
    private String username;
    private String profileName;
    private String pwd;
    private String role;
    private GraduatingYear graduatingYear;
    private GraduatingClass graduatingClass;
}
