package com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;

import java.util.List;

public interface IStudentService {
    Student getFirstStudentByUsrID (String usrID);
    List<Student> getAllStudents();
    boolean addStudent(Student student);
    Student getStudentID(String username);
    boolean updateStudentBio(long studentId, Bio bio);
    List<Bio> getAllBioByGradyear(GraduatingYear graduatingYear);
}
