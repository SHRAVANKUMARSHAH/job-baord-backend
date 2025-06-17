package com.xcelerateit.service.Impl;

import com.xcelerateit.domain.JobResponse;
import com.xcelerateit.repository.JobRepository;
import com.xcelerateit.domain.Job;
import com.xcelerateit.service.JobSearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobSearchServiceImpl implements JobSearchService {

    private final JobRepository jobRepository;

    public JobSearchServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobResponse> search(String location, String skills, String keyword) {
        List<Job> jobs = jobRepository.searchJobs(location, skills, keyword);  // ✅ changed here
        return jobs.stream()
                .map(JobResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse getById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return new JobResponse(job);
    }

    @Override
    public Job addJob(Job job) {
        return jobRepository.save(job);
    }
}
