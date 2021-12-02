package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGraduatingYearRepository extends JpaRepository<GraduatingYear, Long> {
    GraduatingYear getFirstByGraduatingYear(int gradYear);

    GraduatingYear getGraduatingYearById (long id);
}
