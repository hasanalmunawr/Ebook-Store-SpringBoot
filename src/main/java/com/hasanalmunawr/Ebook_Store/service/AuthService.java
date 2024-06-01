package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.LoginRequest;
import com.hasanalmunawr.Ebook_Store.dto.RegisterRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.LoginResponse;

public interface AuthService {

    Integer register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    void activateAccount(Integer tokenCode);
}
