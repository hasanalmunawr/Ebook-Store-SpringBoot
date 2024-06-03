package com.hasanalmunawr.Ebook_Store.book;


import com.fasterxml.jackson.annotation.JacksonInject;
import com.hasanalmunawr.Ebook_Store.user.Auditable;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import com.hasanalmunawr.Ebook_Store.wishlist.WishListEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
    @Column(name = "cover_ebook")
    private byte[] coverEbook;

    @Lob
    private byte[] ebookFile;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    private String language;

    private double rating;

    private boolean availability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wish_list_id", referencedColumnName = "id")
    private WishListEntity wishList;
}
