package com.xcelerateit.endpoints.rest;

import com.xcelerateit.domain.Job;
import com.xcelerateit.domain.JobResponse;
import com.xcelerateit.service.JobSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/jobs")
public class JobPublicController {

    private final JobSearchService jobSearchService;

    public JobPublicController(JobSearchService jobSearchService) {
        this.jobSearchService = jobSearchService;
    }

    private static List<Job> jobList = new ArrayList<>();

    static {
        Job job1 = new Job("Java Developer", "Responsible...", "java", "Pune");
        job1.setId(1L);

        Job job2 = new Job("Backend Engineer", "Build scalable...", "nodejs", "Delhi");
        job2.setId(2L);

        Job job3 = new Job("Frontend Developer", "Create responsive UI...", "react", "Mumbai");
        job3.setId(3L);

        jobList.add(job1);
        jobList.add(job2);
        jobList.add(job3);
    }


    @GetMapping
    public List<Job> getAllJobs(@RequestParam(required = false) String skills,
                                @RequestParam(required = false) String location,
                                @RequestParam(required = false) String keyword) {

        if (skills == null && location == null && keyword == null) {
            return jobList;
        }

        List<Job> filtered = new ArrayList<>();
        for (Job job : jobList) {
            boolean matchesSkills = skills == null || job.getSkills().equalsIgnoreCase(skills);
            boolean matchesLocation = location == null || job.getLocation().equalsIgnoreCase(location);
            boolean matchesKeyword = keyword == null || job.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    job.getDescription().toLowerCase().contains(keyword.toLowerCase());

            if (matchesSkills && matchesLocation && matchesKeyword) {
                filtered.add(job);
            }
        }
        return filtered;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable int id) {
        for (Job job : jobList) {
            if (job.getId() == id) {
                return ResponseEntity.ok(job);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}


