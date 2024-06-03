package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.security.AdminSecurity;
import com.hasanalmunawr.Ebook_Store.service.AdminService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// IT MUST BE ABLE TO ACCESS BY ADMIN ONLY!!!!

@RestController
@RequestMapping("/api/v1/admin")
//@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;
    /*
     * fitur to see profile of admin == principal*/

    @GetMapping(
            path = "/profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProfile(
            @AuthenticationPrincipal UserEntity currentAdmin
            ) {
        if (!AdminSecurity.isAdmin(currentAdmin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
//        UserEntity admin = (UserEntity) currentAdmin.getPrincipal();
        return ResponseEntity.ok(adminService.profile(currentAdmin));
    }

    @PostMapping(path = "/post")
    public ResponseEntity<?> post(
            @AuthenticationPrincipal UserEntity currentAdmin
    ) {
        if (!AdminSecurity.isAdmin(currentAdmin)) {
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
        return ResponseEntity.ok("Hello From POST admin");
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


}
