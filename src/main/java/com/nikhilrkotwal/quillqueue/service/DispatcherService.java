package com.nikhilrkotwal.quillqueue.service;

import com.nikhilrkotwal.quillqueue.repo.JobRepository;
import com.nikhilrkotwal.quillqueue.model.Job;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class DispatcherService {
    private final JobRepository repo;

    public DispatcherService(JobRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public int dispatchDueJobs() {
        List<Job> due = repo.findRunnable(Instant.now());
        int processed = 0;
        for (Job j : due) {
            j.setStatus("RUNNING");
            j.setUpdatedAt(Instant.now());
            repo.save(j);
            // Simulate job execution success
            j.setStatus("SUCCEEDED");
            j.setUpdatedAt(Instant.now());
            repo.save(j);
            processed++;
        }
        return processed;
    }
}
