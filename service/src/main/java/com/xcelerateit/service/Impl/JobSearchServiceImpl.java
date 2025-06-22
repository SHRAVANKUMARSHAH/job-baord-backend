package com.xcelerateit.service.Impl;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;
import com.xcelerateit.repository.JobRepository;
import com.xcelerateit.service.api.JobSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JobSearchServiceImpl implements JobSearchService {

    private final JobRepository jobRepository;

    public JobSearchServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Page<JobResponse> search(String location, String skills, String keyword, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Job> jobPage = jobRepository.searchJobs(location, skills, keyword, pageRequest);
        return jobPage.map(JobResponse::new);
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

    @Override
    public Job updateJob(Long id, Job updatedJob) {
        Job existing = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        existing.setTitle(updatedJob.getTitle());
        existing.setDescription(updatedJob.getDescription());
        existing.setSkills(updatedJob.getSkills());
        existing.setLocation(updatedJob.getLocation());

        return jobRepository.save(existing);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Job not found");
        }
        jobRepository.deleteById(id);
    }
}
