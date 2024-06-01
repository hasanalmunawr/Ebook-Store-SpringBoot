package com.hasanalmunawr.Ebook_Store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @PostMapping(path = "/post")
    public ResponseEntity<?> post() {
        return ResponseEntity.ok("Hello From POST admin");
    }

    @GetMapping(path = "/get")
     public ResponseEntity<?> get() {
        return ResponseEntity.ok("Hello From GET admin");
    }

    @PutMapping("/put")
     public ResponseEntity<?> put() {
        return ResponseEntity.ok("Hello From PUT admin");
    }

    @DeleteMapping("/delete")
     public ResponseEntity<?> delete() {
        return ResponseEntity.ok("Hello From DELETE admin");
    }



}
