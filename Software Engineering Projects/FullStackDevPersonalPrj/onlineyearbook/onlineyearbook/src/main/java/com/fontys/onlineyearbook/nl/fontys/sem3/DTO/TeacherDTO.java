package com.fontys.onlineyearbook.nl.fontys.sem3.DTO;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class TeacherDTO extends Profile {
    private String username;
    private String profileName;
    private String pwd;
    private String role;
    private GraduatingClass graduatingClass;
}
