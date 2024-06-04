package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.hasanalmunawr.Ebook_Store.security.AdminSecurity.*;
import static org.springframework.http.MediaType.APPLICATION_PDF;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/upload/file")
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @RequestParam("filename") String fileName,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);
//        if (file.isEmpty()) {
//            throw new IOException();
//        }
//        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(file, fileName,isbn);
    }

    @PostMapping(path = "/upload/cover")
    public void uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @RequestParam("filename") String fileName,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);

        if (file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveCoverFile(fileBytes, fileName,isbn);
    }

    @GetMapping(path = "/download")
    public ResponseEntity<?> getEbookFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) {
        validateAdminAccess(admin);

        byte[] ebookFile = fileService.getEbookFile(isbn);

        return ResponseEntity.ok()
                .contentType(APPLICATION_PDF)
                .body(ebookFile);
    }

    @GetMapping(path = "/cover")
     public ResponseEntity<?> getCoverFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) {
        validateAdminAccess(admin);

        byte[] ebookFile = fileService.getCoverFile(isbn);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(ebookFile);
    }


    private void validateAdminAccess(UserEntity admin) {
        if (!AdminSecurity.isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

}

