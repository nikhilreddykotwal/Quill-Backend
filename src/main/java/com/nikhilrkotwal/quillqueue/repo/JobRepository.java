package com.nikhilrkotwal.quillqueue.repo;

import com.nikhilrkotwal.quillqueue.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("select j from Job j where j.status='PENDING' and j.runAt <= ?1 order by j.priority desc, j.id asc")
    List<Job> findRunnable(Instant now);
}
