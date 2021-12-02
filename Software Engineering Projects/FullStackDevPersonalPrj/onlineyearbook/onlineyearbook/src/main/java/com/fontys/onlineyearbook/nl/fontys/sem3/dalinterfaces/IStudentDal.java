package com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;

import java.util.List;

public interface IStudentDal {
    Student getFirstStudentWithProfileID(String username);
    List<Bio> getAllBioByGradYearFromDB(GraduatingYear gradYear);
    List<Student> getAllStudentsFromDB();
    void addStudentToDB(Student student);
    Student getStudentID(String username);
    void addBioToStudentDB(long studentID, Bio bio);
}
