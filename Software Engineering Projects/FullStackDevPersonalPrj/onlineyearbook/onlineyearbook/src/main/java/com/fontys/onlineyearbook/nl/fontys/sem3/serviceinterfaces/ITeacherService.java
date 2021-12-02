package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;

import java.util.List;

public interface ITeacherService {
    Teacher getFirstTeacherByUsrID (String usrID);
    List<Teacher> getAllTeachers();
    boolean addTeacher(Teacher teacher);
}
