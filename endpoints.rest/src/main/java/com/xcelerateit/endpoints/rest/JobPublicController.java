package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;
import com.xcelerateit.service.api.JobSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobPublicController {

    @Autowired
    private JobSearchService jobService;
    private final JobSearchService jobSearchService;

    public JobPublicController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    // GET all with filters
    @GetMapping
    public ResponseEntity<Page<JobResponse>> searchJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(jobService.search(location, skills, keyword, page, size));
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
