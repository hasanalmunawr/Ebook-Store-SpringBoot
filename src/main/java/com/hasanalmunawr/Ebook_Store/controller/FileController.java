package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_PDF;

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
        byte[] ebookFile = fileService.getEbookFile(isbn);

        return ResponseEntity.ok()
//                .contentType(APPLICATION_PDF)
                .body(ebookFile);
    }

    @GetMapping(path = "/cover")
    public ResponseEntity<?> getCoverFile(
            @RequestParam("isbn") String isbn,
            @AuthenticationPrincipal UserEntity user
    ) {
        byte[] ebookFile = fileService.getCoverFile(isbn);

        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
                .body(ebookFile);
    }


}

