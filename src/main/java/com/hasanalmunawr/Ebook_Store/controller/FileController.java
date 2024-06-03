package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.hasanalmunawr.Ebook_Store.security.AdminSecurity.*;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/uploadPdf")
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {

        if (!isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        if (!file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(fileBytes, isbn);
    }

    @PostMapping(path = "/uploadCover")
    public void uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        if (!isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        if (!file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(fileBytes, isbn);
    }


}

