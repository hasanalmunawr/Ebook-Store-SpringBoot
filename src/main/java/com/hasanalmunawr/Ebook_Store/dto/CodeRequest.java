package com.hasanalmunawr.Ebook_Store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record CodeRequest(
        @NonNull
        @NotBlank
        Integer code
) {

}
