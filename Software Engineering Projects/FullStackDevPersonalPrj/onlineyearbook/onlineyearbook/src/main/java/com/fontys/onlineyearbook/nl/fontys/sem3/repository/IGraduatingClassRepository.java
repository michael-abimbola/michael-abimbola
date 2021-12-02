package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGraduatingClassRepository extends JpaRepository<GraduatingClass, Long> {
    GraduatingClass getFirstByClassName(String className);

    GraduatingClass getGraduatingClassById(long id);
}
