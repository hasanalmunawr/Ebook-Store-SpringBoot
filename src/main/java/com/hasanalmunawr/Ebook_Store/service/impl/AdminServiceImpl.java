package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.dto.response.ProfileResponse;
import com.hasanalmunawr.Ebook_Store.service.AdminService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import com.hasanalmunawr.Ebook_Store.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public ProfileResponse profile(UserEntity currentAdmin) {
       return ProfileResponse.builder()
               .fistName(currentAdmin.getFirstName())
               .lastName(currentAdmin.getLastName())
               .email(currentAdmin.getEmail())
               .phone(currentAdmin.getPhone())
               .role(currentAdmin.getRole().name())
               .build();
    }
}
