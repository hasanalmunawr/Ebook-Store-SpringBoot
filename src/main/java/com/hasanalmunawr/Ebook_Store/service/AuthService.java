package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.LoginRequest;
import com.hasanalmunawr.Ebook_Store.dto.RegisterRequest;
import com.hasanalmunawr.Ebook_Store.dto.ResetPasswordRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.LoginResponse;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.mail.MessagingException;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    void activateAccount(Integer tokenCode);

    void forgotPassword(String email) throws MessagingException;

    void validatePasswordCode(Integer tokenCode,
                              String newPassword) throws MessagingException;

    void updatePassword(UserEntity currentUser) throws MessagingException;

    void changePassword(UserEntity currentUser,
                        Integer code,
                        ResetPasswordRequest request) throws MessagingException;
}
