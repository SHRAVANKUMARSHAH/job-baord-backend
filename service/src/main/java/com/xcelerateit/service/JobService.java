package com.xcelerateit.service;

import com.xcelerateit.domain.Job;
import java.util.List;

public interface JobService {
    Job addJob(Job job);
    List<Job> getAllJobs();
    Job getJobById(Long id);
    void deleteJob(Long id);
}
