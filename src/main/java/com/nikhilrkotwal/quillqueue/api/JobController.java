package com.nikhilrkotwal.quillqueue.api;

import com.nikhilrkotwal.quillqueue.model.Job;
import com.nikhilrkotwal.quillqueue.repo.JobRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobRepository repo;
    public JobController(JobRepository repo) { this.repo = repo; }

    @PostMapping
    public Job enqueue(@RequestBody Job job) {
        job.setStatus("PENDING");
        job.setCreatedAt(Instant.now());
        job.setUpdatedAt(Instant.now());
        if (job.getRunAt() == null) job.setRunAt(Instant.now());
        if (job.getMaxAttempts() == 0) job.setMaxAttempts(3);
        return repo.save(job);
    }

    @GetMapping
    public List<Job> list() { return repo.findAll(); }

    @PostMapping("/retry/{id}")
    public ResponseEntity<Job> retry(@PathVariable Long id) {
        return repo.findById(id).map(j -> {
            j.setStatus("PENDING");
            j.setAttempts(0);
            j.setUpdatedAt(Instant.now());
            return ResponseEntity.ok(repo.save(j));
        }).orElse(ResponseEntity.notFound().build());
    }
}
