package com.veracode.hackathon.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @PostMapping(value = "send-flaws", consumes = "application/json")
    public ResponseEntity<String> sendFlawInformation(@RequestBody List<Flaw> flaws) {
        System.out.println("Sending flaws information");
        this.template.setDefaultDestination("/topic/flaws");
        this.template.convertAndSend(flaws);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/send-scan-status")
    public void sendScanStatus() throws Exception {
        System.out.println("Sending scan status information");
        this.template.setDefaultDestination("/topic/scan-status");

        Thread.sleep(2000);
        System.out.println("Sending scan status: In Progress");

        this.template.convertAndSend("In Progress");
        Thread.sleep(3000);
        System.out.println("Sending scan status: Completed");
        this.template.convertAndSend("Completed");

        Thread.sleep(2000);
        System.out.println("Sending scan status: Results Ready");
        this.template.convertAndSend("Results Ready");

        sendFlawInformation(getFlaws());

    }

    private List<Flaw> getFlaws() {
        List<Flaw> flaws = new ArrayList<>();
        Flaw flaw = new Flaw();
        flaw.setId(1);
        flaw.setSeverity("Low");
        flaw.setDescription("Improperty Output Neutralization");
        flaws.add(flaw);

        flaw = new Flaw();
        flaw.setId(2);
        flaw.setSeverity("Medium");
        flaw.setDescription("Cross-Site Request Forgery (CSRF)");
        flaws.add(flaw);

        flaw = new Flaw();
        flaw.setId(3);
        flaw.setSeverity("High");
        flaw.setDescription("SQL Injection");
        flaws.add(flaw);

        return flaws;
    }
}
