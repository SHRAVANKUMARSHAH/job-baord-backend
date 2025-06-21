package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;
import com.xcelerateit.service.api.JobSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPublicController {

    private final JobSearchService jobSearchService;

    public JobPublicController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    // GET all with filters
    @GetMapping
    public List<JobResponse> searchJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String keyword) {
        return jobSearchService.search(location, skills, keyword);
    }

    // GET by ID
    @GetMapping("/{id}")
    public JobResponse getJobById(@PathVariable Long id) {
        return jobSearchService.getById(id);
    }

    // POST: Create a new job
    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobSearchService.addJob(job);
    }

    // PUT: Update a job
    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobSearchService.updateJob(id, job);
    }

    // DELETE: Delete a job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        jobSearchService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}
