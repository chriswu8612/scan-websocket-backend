package com.veracode.hackathon.scan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
public class FileUploadController {

    @PostMapping(value = "/upload", produces = "application/json")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {

        System.out.println("Received file " + file.getOriginalFilename());
        return ResponseEntity.ok("File Upload Success!");
    }

}
