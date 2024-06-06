package com.hasanalmunawr.Ebook_Store.dto;

public record ResetPasswordRequest(
        String email,
        String oldPassword,
        String newPassword,
        String passwordConfirm
) {
}
