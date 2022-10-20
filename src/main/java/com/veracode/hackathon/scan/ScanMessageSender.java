package com.veracode.hackathon.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScanMessageSender {
    @Autowired
    private SimpMessagingTemplate template;

    @PostMapping("/send-message")
    public void sendScanMessage() {
        System.out.println("Sending scan message");
        this.template.convertAndSend("/topic/scans");
    }

    @PostMapping(value = "send-modules", consumes = "application/json")
    public ResponseEntity<String> sendModuleInformation(@RequestBody List<Module> modules) {
        System.out.println("Sending module information");
        this.template.setDefaultDestination("/topic/modules");
        this.template.convertAndSend(modules);
        return ResponseEntity.ok("Success");
    }
}
