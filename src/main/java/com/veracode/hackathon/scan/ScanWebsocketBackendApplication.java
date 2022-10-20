package com.veracode.hackathon.scan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@SpringBootApplication
public class ScanWebsocketBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScanWebsocketBackendApplication.class, args);
	}
}