package com.hasanalmunawr.Ebook_Store.controller;


import com.hasanalmunawr.Ebook_Store.dto.response.SearchResponse;
import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ebook")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/filterByPrice")
    public ResponseEntity<List<SearchResponse>> FilterEbookByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceRange(min, max));
    }

    @GetMapping(path = "/filterByTitle")
    public ResponseEntity<List<SearchResponse>> FilterEbookByTitle(
            @RequestParam("title") String title,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByTitle(title));
    }

    @GetMapping(path = "/filterByAuthor")
    public ResponseEntity<List<SearchResponse>> FilterEbookByAuthor(
            @RequestParam("author") String author,
            Authentication currentUser
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByAuthor(author));
    }

    @GetMapping(path = "/filterByPriceAsc")
    public ResponseEntity<List<SearchResponse>> FilterEbookByPriceCheaper(
            Authentication currentUser
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceCheaper());
    }

    @GetMapping(path = "/filterByPriceDsc")
    public ResponseEntity<List<SearchResponse>> FilterEbookByPriceExpensive(
            Authentication currentUser
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceExpensive());
    }


    @GetMapping(path = "/get-message")
    public ResponseEntity<?> post() {
        return ResponseEntity.ok("Hello From GEt");
    }
}
