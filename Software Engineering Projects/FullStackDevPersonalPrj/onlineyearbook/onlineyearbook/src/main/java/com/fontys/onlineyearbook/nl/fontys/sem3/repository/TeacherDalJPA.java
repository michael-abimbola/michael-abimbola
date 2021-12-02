package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.ITeacherDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDalJPA implements ITeacherDal {

    @Autowired
    ITeacherRepository repo;

    @Override
    public Teacher getFirstTeacherWithProfileID(String username) {
        return repo.getFirstTeacherByUsername(username);
    }

    @Override
    public List<Teacher> getAllTeachersFromDB() {
        return repo.findAll();
    }

    @Override
    public void addTeacherToDB(Teacher teacher) {
        repo.save(teacher);
    }
}
