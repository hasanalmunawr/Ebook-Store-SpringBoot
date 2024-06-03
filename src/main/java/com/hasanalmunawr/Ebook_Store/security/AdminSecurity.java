package com.hasanalmunawr.Ebook_Store.security;

import com.hasanalmunawr.Ebook_Store.user.Role;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;

import java.util.Objects;

import static com.hasanalmunawr.Ebook_Store.user.Role.ADMIN;

public class AdminSecurity {

    public static boolean isAdmin(UserEntity user) {
        if (!Objects.equals(user.getRole(), ADMIN)) {
            return false;
        }
        return true;
    }
}
