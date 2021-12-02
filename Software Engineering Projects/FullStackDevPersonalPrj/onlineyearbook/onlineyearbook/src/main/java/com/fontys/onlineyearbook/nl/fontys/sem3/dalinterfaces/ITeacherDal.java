package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;

import java.util.List;

public interface ITeacherDal {
    Teacher getFirstTeacherWithProfileID(String username);
    List<Teacher> getAllTeachersFromDB();
    void addTeacherToDB(Teacher teacher);
}
