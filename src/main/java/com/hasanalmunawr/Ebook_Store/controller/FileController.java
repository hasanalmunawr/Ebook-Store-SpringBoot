package com.hasanalmunawr.Ebook_Store.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {

    @PostMapping("/uploadPdf")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                // You can now save the bytes to a file or a database as needed

                return "You successfully uploaded the file!";
            } catch (Exception e) {
                return "You failed to upload the file => " + e.getMessage();
            }
        } else {
            return "You failed to upload the file because it was empty.";
        }
    }

    @PostMapping(path = "/uploadCover")
    public void uploadCover(@RequestParam("file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
        }
    }
}

