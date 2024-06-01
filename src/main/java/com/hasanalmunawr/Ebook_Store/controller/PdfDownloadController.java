package com.hasanalmunawr.Ebook_Store.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class PdfDownloadController {

    @GetMapping("/downloadPdf/{filename}")
    public ResponseEntity<InputStreamResource> downloadPDFFile(@PathVariable String filename) throws FileNotFoundException {
        // Assuming the PDF file is in the 'files' directory of the project
        String filePath = "files/" + filename;

        // Create a FileInputStream for the PDF file
        FileInputStream fileStream = new FileInputStream(filePath);

        // Set the content type and header for the response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        // Return the PDF file as an InputStreamResource
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(fileStream));
    }
}
