package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/uploadPdf")
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn
    ) throws IOException {
        if (!file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(fileBytes, isbn);
    }

    @PostMapping(path = "/uploadCover")
    public void uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn
    ) throws IOException {
        if (!file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(fileBytes, isbn);
    }
}

