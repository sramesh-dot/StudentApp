package com.example.studentApp.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class StudentScheduler {

    @Scheduled(
            fixedRate = 10000,
            initialDelay = 5000
    )
    public void runTask() {
        System.out.println(
                "Scheduled Task Running: "
                + LocalDateTime.now()
        );
    }

}
