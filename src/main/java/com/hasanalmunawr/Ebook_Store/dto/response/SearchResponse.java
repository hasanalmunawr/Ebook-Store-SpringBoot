package com.hasanalmunawr.Ebook_Store.dto.response;

import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchResponse {

    private String title;
    private String isbn;
    private String publisher;
    private String category;
    private String description;
    private double price;
    private String language;

    private LocalDate publicationDate;
    private int numberOfPages;
    private double rating;

    private byte[] coverEbook;
    private byte[] ebookFile;
    private boolean availability;
}
