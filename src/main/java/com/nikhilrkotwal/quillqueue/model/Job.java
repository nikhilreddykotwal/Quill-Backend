package com.nikhilrkotwal.quillqueue.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queueName;
    @Column(columnDefinition = "TEXT")
    private String payload; // JSON
    private int priority; // higher = sooner
    private int attempts;
    private int maxAttempts;
    private String status; // PENDING, RUNNING, SUCCEEDED, FAILED, DEAD_LETTER
    private Instant runAt;
    private Instant createdAt;
    private Instant updatedAt;
}
