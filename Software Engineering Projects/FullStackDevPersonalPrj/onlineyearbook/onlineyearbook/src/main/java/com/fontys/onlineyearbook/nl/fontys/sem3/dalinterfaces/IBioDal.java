package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;

public interface IBioDal {
    void addBioT0DB(Bio studentBio);

    Bio getBioIDByFulNameFromDB(String fName);
}
