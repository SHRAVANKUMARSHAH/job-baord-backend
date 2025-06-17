package com.xcelerateit.service;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;

import java.util.List;


public interface JobSearchService {
    List<JobResponse> search(String location, String skills, String keyword);
    JobResponse getById(Long id);

    Job addJob(Job job);
}
