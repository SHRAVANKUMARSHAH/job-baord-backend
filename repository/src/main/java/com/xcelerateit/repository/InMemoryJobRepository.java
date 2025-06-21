package com.xcelerateit.repository;

import com.xcelerateit.domain.Job;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public abstract class InMemoryJobRepository implements JobRepository {
    private final Map<Long, Job> store = new HashMap<>();
    private Long idCounter = 1L;

    @Override
    public Job save(Job job) {
        job.setId(idCounter++);
        store.put(job.getId(), job);
        return job;
    }

    @Override
    public List<Job> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Job> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }
}
