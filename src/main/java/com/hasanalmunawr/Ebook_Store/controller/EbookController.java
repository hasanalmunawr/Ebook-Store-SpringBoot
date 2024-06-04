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
import org.springframework.http.HttpStatusCode;
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
    
    @GetMapping("/getInfo/{isbn}")
    public ResponseEntity<?> getEbookInformation(
            @PathVariable String isbn,
            @AuthenticationPrincipal UserEntity user) {
       return ResponseEntity.ok(ebookService.getEbookInformation(isbn));
    }


}
