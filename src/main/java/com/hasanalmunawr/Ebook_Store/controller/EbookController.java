package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/ebook")
@RequiredArgsConstructor
public class EbookController {

    private final EbookService ebookService;
    private final FileService fileService;
    private final EbookRepository ebookRepository;

    @PostMapping("/uploadInfo")
    public void ebookInformation(
            @RequestBody EbookRequest request,
            @AuthenticationPrincipal UserEntity currentAdmin
            ) {
      validateAdminAccess(currentAdmin);
        ebookService.saveEbookInformation(request, currentAdmin);
    }

    @PostMapping(path = "/uploadCover")
    public ResponseEntity<?> uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);
        if (!file.isEmpty()) {
            throw new IOException();
        }

        fileService.saveEbookCover(file.getBytes(), isbn);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<?> handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);
        if (file.isEmpty()) {
            throw new IOException();
        }

        fileService.saveEbookFile(file.getBytes(), isbn);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/download")
    public ResponseEntity<?> downloadFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin) {
        validateAdminAccess(admin);

        byte[] downloadFile;
//        try {
//            downloadFile = fileService.downloadFile(isbn);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error downloading file: " + e.getMessage());
//        }

        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        downloadFile = ebookEntity.getEbookFile();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(downloadFile);
    }

     @GetMapping(path = "/seeCover")
    public ResponseEntity<?> seeCover(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin) {
        validateAdminAccess(admin);

        byte[] downloadFile;
        try {
            downloadFile = fileService.seeCover(isbn);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error downloading file: " + e.getMessage());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(downloadFile);
    }




    private void validateAdminAccess(UserEntity admin) {
        if (!AdminSecurity.isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

}
