package com.hasanalmunawr.Ebook_Store.controller;


import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ebook")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/filterByPrice")
    public ResponseEntity<List<EbookResponse>> FilterEbookByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceRange(min, max));
    }

    @GetMapping(path = "/filterByTitle")
    public ResponseEntity<List<EbookResponse>> FilterEbookByTitle(
            @RequestParam("title") String title,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByTitle(title));
    }

    @GetMapping(path = "/filterByAuthor")
    public ResponseEntity<List<EbookResponse>> FilterEbookByAuthor(
            @RequestParam("author") String author,
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByAuthor(author));
    }

    @GetMapping(path = "/filterByPriceAsc")
    public ResponseEntity<List<EbookResponse>> FilterEbookByPriceCheaper(
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceCheaper());
    }

    @GetMapping(path = "/filterByPriceDsc")
    public ResponseEntity<List<EbookResponse>> FilterEbookByPriceExpensive(
            @AuthenticationPrincipal UserEntity user
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceExpensive());
    }




    @GetMapping(path = "/get-message")
    public ResponseEntity<?> post() {
        return ResponseEntity.ok("Hello From GEt");
    }
}
