package com.hasanalmunawr.Ebook_Store.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("Activate Account"),
    RESET_PASSWORD("Reset Password"),
    UPDATE_PASSWORD("Update Password");
    ;

    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
}