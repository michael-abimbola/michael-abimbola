package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IStudentRepository extends JpaRepository<Student, Long> {
    Student getFirstStudentByUsername(String username);

    @Query("SELECT s FROM Student As s INNER JOIN Profile AS p ON s.id = p.id WHERE p.username = ?1 ")
    Student getStudentIDByUsername(String username);

    @Query("SELECT b FROM Bio AS b INNER JOIN Student AS s ON b = s.studentBio WHERE s.graduatingYear = ?1")
    List<Bio> getAllBioByGradYear(GraduatingYear gradYear);

    @Modifying
    @Transactional
    @Query("UPDATE Student As s SET s.studentBio = ?2 WHERE s.id = ?1")
    int updateStudentProfileBio(long studentID, Bio bio);

}
