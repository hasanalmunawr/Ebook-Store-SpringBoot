package com.hasanalmunawr.Ebook_Store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;


public record LoginRequest(
        @NonNull
        @NotBlank
        String email,
        @NonNull
        @NotBlank
        String password) {
}
