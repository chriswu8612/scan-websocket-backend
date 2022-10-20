package com.veracode.hackathon.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

// @EnableScheduling
@Configuration
public class ScheduleConfig {
    private static int counter = 1;
    @Autowired
    SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 3000)
    public void sendAdhocMessages() {
        System.out.println("Sending adhoc messages");
        template.setDefaultDestination("/topic/scans");
        template.convertAndSend("message " + counter);
        counter++;
    }
}
