package com.hasanalmunawr.Ebook_Store.transaction;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String ebookName;
    private Integer ebookId;
    private LocalDateTime transactionDateTime;
    private Integer amount;
    private Double Price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;

    @ManyToMany
    @JoinTable(
            name = "transaction_ebook",
            joinColumns = @JoinColumn(
                    name = "transaction_id"
            ), inverseJoinColumns = @JoinColumn(
            name = "ebook_id")
    )
    private List<EbookEntity> ebooks;


}
