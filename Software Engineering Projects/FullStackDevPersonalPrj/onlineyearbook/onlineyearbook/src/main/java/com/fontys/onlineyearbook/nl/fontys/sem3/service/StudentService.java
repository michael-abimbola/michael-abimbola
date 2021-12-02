package com.fontys.onlineyearbook.nl.fontys.sem3.service;

import com.fontys.onlineyearbook.nl.fontys.sem3.dalinterfaces.IStudentDal;
import com.fontys.onlineyearbook.nl.fontys.sem3.exceptions.NullFieldsException;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;
import com.fontys.onlineyearbook.nl.fontys.sem3.serviceinterfaces.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService {
    IStudentDal dal;
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public StudentService(IStudentDal dal, BCryptPasswordEncoder passwordEncoder){
        this.dal = dal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student getFirstStudentByUsrID (String usrID){
        return dal.getFirstStudentWithProfileID(usrID);
    }

    @Override
    public List<Student> getAllStudents() {
        return dal.getAllStudentsFromDB();
    }

    @Override
    public boolean addStudent(Student student) {
        if(student.getUsername().isEmpty() || student.getPwd().isEmpty() || student.getRole().isEmpty() || student.getProfileName().isEmpty() || student.getGraduatingClass().getId() == 0 || student.getGraduatingYear().getId() == 0){
            throw new NullFieldsException("one of the required fields is empty");
        }
        student.setPwd(passwordEncoder.encode(student.getPwd()));
        dal.addStudentToDB(student);
        return true;
    }

    @Override
    public Student getStudentID(String username) {
        return dal.getStudentID(username);
    }

    @Override
    public boolean updateStudentBio(long studentId, Bio bio) {
        dal.addBioToStudentDB(studentId, bio);
        return true;
    }

    @Override
    public List<Bio> getAllBioByGradyear(GraduatingYear graduatingYear) {
        return dal.getAllBioByGradYearFromDB(graduatingYear);
    }
}
