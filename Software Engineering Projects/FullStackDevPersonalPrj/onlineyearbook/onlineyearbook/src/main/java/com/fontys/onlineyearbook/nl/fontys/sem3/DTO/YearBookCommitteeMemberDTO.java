package com.fontys.onlineyearbook.nl.fontys.sem3.DTO;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class YearBookCommitteeMemberDTO extends Profile {
    private String username;
    private String profileName;
    private String pwd;
    private String role;
}
