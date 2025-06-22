package com.xcelerateit.repository;

import com.xcelerateit.domain.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // 🔴 Important!
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE " +
            "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
            "(:skills IS NULL OR LOWER(j.skills) LIKE LOWER(CONCAT('%', :skills, '%'))) AND " +
            "(:keyword IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Job> searchJobs(
            @Param("location") String location,
            @Param("skills") String skills,
            @Param("keyword") String keyword,
            Pageable pageable
    );
}
