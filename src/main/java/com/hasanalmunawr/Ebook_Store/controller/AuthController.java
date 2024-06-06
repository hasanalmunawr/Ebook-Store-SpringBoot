package com.hasanalmunawr.Ebook_Store.controller;

import com.hasanalmunawr.Ebook_Store.dto.CodeRequest;
import com.hasanalmunawr.Ebook_Store.dto.LoginRequest;
import com.hasanalmunawr.Ebook_Store.dto.RegisterRequest;
import com.hasanalmunawr.Ebook_Store.dto.ResetPasswordRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.LoginResponse;
import com.hasanalmunawr.Ebook_Store.service.AuthService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView register(
            @RequestBody RegisterRequest registerRequest
    ) {
        authService.register(registerRequest);

        return new RedirectView("/api/v1/auth/activate-account");
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
    ) {
        authService.activateAccount(request.code());
    }


    @PostMapping(path = "/forgot-password")
    public RedirectView post(
            @RequestBody ResetPasswordRequest request
    ) throws MessagingException {
        authService.forgotPassword(request.email());
        return new RedirectView("/api/v1/auth/validate/password-code");
    }

    @PutMapping(path = "/validate/password-code")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void validatePasswordCode(
            @RequestBody CodeRequest request
    ) throws MessagingException {
        authService.validatePasswordCode(request.code(), request.newPassword());
    }


}
