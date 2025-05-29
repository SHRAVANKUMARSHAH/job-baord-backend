package com.xcelerateit.repository;

import com.xcelerateit.domain.Job;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface JobRepository {
    Job save(Job job);
    List<Job> findAll();
    Optional<Job> findById(Long id);
    void deleteById(Long id);
}
