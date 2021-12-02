package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Profile As p SET p.profileName = ?1, p.pwd = ?2 WHERE p.username = ?3")
    void updateProfile(String profileName, String pwd, String username);
}
