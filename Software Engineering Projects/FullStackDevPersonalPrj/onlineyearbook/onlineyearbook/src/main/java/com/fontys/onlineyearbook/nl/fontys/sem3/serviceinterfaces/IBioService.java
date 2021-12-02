package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;

public interface IBioService {
    boolean addStudentBio(Bio studentBio);

    Bio getBioIDByFullName(String fName);
}
