package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.response.ProfileResponse;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;

public interface AdminService {

    ProfileResponse profile(UserEntity currentAdmin);
}
