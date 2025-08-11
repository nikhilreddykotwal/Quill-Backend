package com.nikhilrkotwal.quillqueue.scheduler;

import com.nikhilrkotwal.quillqueue.service.DispatcherService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DispatcherScheduler {
    private final DispatcherService dispatcherService;
    public DispatcherScheduler(DispatcherService dispatcherService) {
        this.dispatcherService = dispatcherService;
    }

    // Run every 5 seconds
    @Scheduled(fixedDelay = 5000)
    public void tick() {
        dispatcherService.dispatchDueJobs();
    }
}
