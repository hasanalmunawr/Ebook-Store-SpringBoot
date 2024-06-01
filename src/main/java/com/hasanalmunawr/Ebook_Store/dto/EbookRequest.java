package com.hasanalmunawr.Ebook_Store.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EbookRequest {

    @NonNull
    @NotBlank
    private String title;

    @NonNull
    @NotBlank
    private String author;

    @NonNull
    @NotBlank
    private String isbn;

    private String publisher;

    private String category;

    @Size(max = 2000)
    private String description;

    private BigDecimal price;

    private String language;

    private int numberOfPages;

}
