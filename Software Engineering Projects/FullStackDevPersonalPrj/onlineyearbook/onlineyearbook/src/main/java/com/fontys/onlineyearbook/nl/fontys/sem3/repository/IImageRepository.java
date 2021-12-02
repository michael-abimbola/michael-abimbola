package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingClass;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.GraduatingYear;
import com.fontys.onlineyearbook.nl.fontys.sem3.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Long>{
    @Query("SELECT im FROM Image im WHERE im.graduatingYear = ?1 AND im.available = true")
    List<Image> getAllImagesByGradYear(GraduatingYear gradYear);

    @Query("SELECT im FROM Image im WHERE im.graduatingYear = ?1 AND im.category = ?2 AND im.available = true")
    List<Image> getAllImagesByGradYearAndCategory(GraduatingYear gradYear, String category);

    @Query("SELECT im FROM Image im WHERE im.graduatingClass = ?1 AND im.graduatingYear = ?2 AND im.available = true")
    List<Image> getAllImagesByGradClass(GraduatingClass gradClass, GraduatingYear graduatingYear);

    @Query("SELECT im FROM Image im WHERE im.available = false")
    List<Image> getAllUnAvailableImages();

    @Modifying
    @Transactional
    @Query("UPDATE Image im SET im.available = ?1 WHERE im.id = ?2")
    void imageAvailable(boolean available, long id);

    Image getImageById(long id);

}
