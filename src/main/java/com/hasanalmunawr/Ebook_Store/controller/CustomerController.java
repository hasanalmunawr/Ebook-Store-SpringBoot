package com.hasanalmunawr.Ebook_Store.controller;


import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
@RequiredArgsConstructor
public class CustomerController {

    private final EbookService ebookService;
    private final CustomerService customerService;

    public ResponseEntity<?> FilterEbookByPriceRange(
            @RequestParam("min") double min,
            @RequestParam("max") double max
    ) {
        return ResponseEntity.ok(customerService.FilterEbookByPriceRange(min, max));
    }

    public ResponseEntity<List<Eb>> FilterEbookByPriceCheaper() {

    }

    public void FilterEbookByPriceExpensive() {

    }

    public void FilterEbookByTitle() {

    }

    public void FilterEbookByAuthor() {

    }
}
