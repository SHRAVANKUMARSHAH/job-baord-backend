package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.Job;
import com.xcelerateit.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.addJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public Job getJob(@PathVariable("id") Long id) {
        return jobService.getJobById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable("id") Long id) {
        jobService.deleteJob(id);
    }
}
