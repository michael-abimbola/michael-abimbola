package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher getFirstTeacherByUsername(String username);
}
