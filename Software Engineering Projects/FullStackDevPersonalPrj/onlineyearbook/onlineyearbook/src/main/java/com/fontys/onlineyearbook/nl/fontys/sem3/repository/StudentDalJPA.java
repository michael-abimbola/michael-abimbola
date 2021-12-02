package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IStudentDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDalJPA implements IStudentDal {
    @Autowired
    IStudentRepository repo;

    @Override
    public Student getFirstStudentWithProfileID(String username) {
        return repo.getFirstStudentByUsername(username);
    }

    @Override
    public List<Bio> getAllBioByGradYearFromDB(GraduatingYear gradYear) {
        List<Bio> studentBios = (List<Bio>) repo.getAllBioByGradYear(gradYear);
        return studentBios;
    }

    @Override
    public List<Student> getAllStudentsFromDB() {
        return repo.findAll();
    }

    @Override
    public void addStudentToDB(Student student) {
        repo.save(student);
    }

    @Override
    public Student getStudentID(String username) {
        return repo.getStudentIDByUsername(username);
    }

    @Override
    public void addBioToStudentDB(long studentID, Bio bio) {
        repo.updateStudentProfileBio(studentID, bio);
    }
}
