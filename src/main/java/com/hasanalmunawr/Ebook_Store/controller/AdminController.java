package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.UpdateEbookRequest;
import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.AdminService;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
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
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;
    private final EbookService ebookService;
    private final FileService fileService;

    @GetMapping(
            path = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProfile(
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        validateAdminAccess(currentAdmin);
        return ResponseEntity.ok(adminService.profile(currentAdmin));
    }

    @PostMapping(
            path = "/uploadInfo",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void ebookInformation(
            @RequestBody EbookRequest request,
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        validateAdminAccess(currentAdmin);
        ebookService.saveEbookInformation(request, currentAdmin);
    }

    @PostMapping("/upload/file")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);
        if (file.isEmpty()) {
            throw new IOException();
        }

        fileService.saveEbookFile(file, isbn);
    }

    @PostMapping(path = "/upload/cover")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);

        if (file.isEmpty()) {
            throw new IOException();
        }

        fileService.saveCoverFile(file, isbn);
    }

    @PutMapping(
            value = "/update-ebook/{isbn}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(
            @RequestBody UpdateEbookRequest request,
            @PathVariable String isbn,
            @AuthenticationPrincipal UserEntity currentAdmin) {
        validateAdminAccess(currentAdmin);
        return ResponseEntity.accepted().body(ebookService.updateEbookInformation(request, isbn, currentAdmin));
    }

    @DeleteMapping("/delete/{isbn}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable String isbn,
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        validateAdminAccess(currentAdmin);
        ebookService.deleteEbook(isbn);
    }


    private void validateAdminAccess(UserEntity admin) {
        if (!AdminSecurity.isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

}
