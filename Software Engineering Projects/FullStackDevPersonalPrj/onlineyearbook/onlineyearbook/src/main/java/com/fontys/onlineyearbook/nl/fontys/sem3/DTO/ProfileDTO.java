package com.fontys.onlineyearbook.nl.fontys.sem3.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@ToString
public class ProfileDTO {
    private String username;
    private String profileName;
    private String pwd;
    private String role;
}
