package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.ITeacherDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements ITeacherService {
    ITeacherDal dal;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public TeacherService(ITeacherDal dal, BCryptPasswordEncoder passwordEncoder){
        this.dal = dal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Teacher getFirstTeacherByUsrID (String usrID){
        return dal.getFirstTeacherWithProfileID(usrID);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return dal.getAllTeachersFromDB();
    }

    @Override
    public boolean addTeacher(Teacher teacher) {
        if(teacher.getUsername().isEmpty() || teacher.getPwd().isEmpty() || teacher.getRole().isEmpty() || teacher.getProfileName().isEmpty() || teacher.getGraduatingClass().getId() == 0){
            throw new NullFieldsException("one of the required fields is empty");
        }
        teacher.setPwd(passwordEncoder.encode(teacher.getPwd()));
        dal.addTeacherToDB(teacher);
        return true;
    }
}
