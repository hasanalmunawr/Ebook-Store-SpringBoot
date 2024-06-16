package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @GetMapping(path = "/download")
    public ResponseEntity<?> getEbookFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity user
    ) {
        String ebookFile = String.valueOf(fileService.getEbookFile(isbn));

        return ResponseEntity.ok()
                .body(ebookFile);
    }

    @GetMapping(path = "/cover")
    public ResponseEntity<?> getCoverFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity user
    ) {
         String ebookFile = String.valueOf(fileService.getCoverFile(isbn));

        return ResponseEntity.ok()
                .body(ebookFile);
    }


}

