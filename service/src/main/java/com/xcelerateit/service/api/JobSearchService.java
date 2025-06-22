package com.xcelerateit.service.api;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;
import org.springframework.data.domain.Page;

public interface JobSearchService {
    public Page<JobResponse> search(String location, String skills, String keyword, int page, int size);
    JobResponse getById(Long id);
    Job addJob(Job job);
    Job updateJob(Long id, Job updatedJob);
    void deleteJob(Long id);
}
