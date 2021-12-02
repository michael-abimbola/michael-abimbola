package com.fontys.onlineyearbook.nl.fontys.sem3.repository;

import com.fontys.onlineyearbook.nl.fontys.sem3.model.YearBookCommitteeMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IYearBookCommitteeMemberRepository extends JpaRepository<YearBookCommitteeMember, Long> {
    YearBookCommitteeMember getFirstYBCMByUsername(String username);
}
