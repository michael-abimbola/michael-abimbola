package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Bio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBioRepository extends JpaRepository<Bio, Long> {
    @Query("SELECT b FROM Bio As b WHERE b.fullName = ?1")
    Bio getBioIDByFullName(String fName);
}
