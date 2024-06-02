package com.hasanalmunawr.Ebook_Store.book;


import com.hasanalmunawr.Ebook_Store.user.Auditable;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ebooks")
public class EbookEntity extends Auditable implements Serializable {


    @Column(nullable = false)
    private String title;

    @ManyToOne()
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private UserEntity author;

    @Column(nullable = false, unique=true)
    private String isbn;

    private String publisher;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    private String category;

    @Column(length = 2000)
    private String description;

    @Column(nullable = false)
    private double price;

    @Lob
    private byte[] ebookFile;

    private String language;

//    private PDFDoc
    @Column(name = "number_of_pages")
    private int numberOfPages;

//    private String format;

    @Column(name = "cover_ebook")
    private byte[] coverEbook;

    private double rating;

//    @Column(name = "file_url")
//    private String fileUrl;

    private boolean availability;
}
