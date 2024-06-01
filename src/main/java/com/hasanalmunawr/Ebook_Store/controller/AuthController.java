package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.dto.CodeRequest;
import com.hasanalmunawr.Ebook_Store.dto.LoginRequest;
import com.hasanalmunawr.Ebook_Store.dto.RegisterRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.LoginResponse;
import com.hasanalmunawr.Ebook_Store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(
            path = "/register",
            consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return authService.register(registerRequest);
    }

    @PostMapping(
            path = "/login",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest registerRequest
    ) {
        return ResponseEntity.ok(authService.login(registerRequest));
    }

    @PostMapping(path = "/activate-account")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void activateAccount(
            @RequestBody CodeRequest request
    )  {
        authService.activateAccount(request.code());
    }
}
