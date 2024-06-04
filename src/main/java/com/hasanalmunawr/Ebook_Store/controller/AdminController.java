package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.AdminService;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
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

// IT MUST BE ABLE TO ACCESS BY ADMIN ONLY!!!!

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
//        UserEntity admin = (UserEntity) currentAdmin.getPrincipal();
        return ResponseEntity.ok(adminService.profile(currentAdmin));
    }

    @PostMapping("/uploadInfo")
    public void ebookInformation(
            @RequestBody EbookRequest request,
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        validateAdminAccess(currentAdmin);
        ebookService.saveEbookInformation(request, currentAdmin);
    }

    @PostMapping("/upload/file")
    public void handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @RequestParam("fileName") String fileName,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);
        if (file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveEbookFile(file, fileName,isbn);
    }

    @PostMapping(path = "/upload/cover")
    public void uploadCover(
            @RequestParam("file") MultipartFile file,
            @RequestParam("isbn") String isbn,
            @RequestParam("fileName") String fileName,
            @AuthenticationPrincipal UserEntity admin
    ) throws IOException {
        validateAdminAccess(admin);

        if (file.isEmpty()) {
            throw new IOException();
        }
        byte[] fileBytes = file.getBytes();

        fileService.saveCoverFile(file, fileName,isbn);
    }

    @PutMapping("/put")
    public ResponseEntity<?> put(
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        if (!AdminSecurity.isAdmin(currentAdmin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        return ResponseEntity.ok("Hello From PUT admin");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        if (!AdminSecurity.isAdmin(currentAdmin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        return ResponseEntity.ok("Hello From DELETE admin");
    }


    private void validateAdminAccess(UserEntity admin) {
        if (!AdminSecurity.isAdmin(admin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

}
