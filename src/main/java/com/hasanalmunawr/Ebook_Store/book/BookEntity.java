package com.hasanalmunawr.Ebook_Store.book;


import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookEntity {


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique=true)
    private String isbn;

    private String publisher;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String category;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    private String language;

//    private PDFDoc
    @Column(name = "number_of_pages")
    private int numberOfPages;

    private String format;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    private double rating;

    @Column(name = "file_url")
    private String fileUrl;

    private boolean availability;
}
