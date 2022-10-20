package com.veracode.hackathon.scan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public class ScanMessageController {
    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/scan-status")
    @SendTo("/topic/scans")
    public ScanMessage scanStatus() throws Exception {
        System.out.println("Invoking scan status");
        Thread.sleep(1000); // simulated delay
        ScanMessage msg = new ScanMessage();
        msg.setId(1907);
        msg.setStatus("in progress");
        return msg;
    }


}
