package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.TransactionRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.TransactionResponse;
import com.hasanalmunawr.Ebook_Store.service.TransactionService;
import com.hasanalmunawr.Ebook_Store.transaction.TransactionEntity;
import com.hasanalmunawr.Ebook_Store.transaction.TransactionRepository;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import com.hasanalmunawr.Ebook_Store.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.TransactionalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final EbookRepository ebookRepository;

    @Override
    public TransactionResponse createTransaction(TransactionRequest transaction, UserEntity user) {

        var ebookEntity = ebookRepository.findByIsbn(transaction.ebookIsbn())
                .orElseThrow(EntityNotFoundException::new);

        if (!ebookEntity.isAvailability()) {
           throw new RequestRejectedException("Ebook is not available.");
        }

        double price = ebookEntity.getPrice();
        double totalPrice = price * transaction.amount();

        TransactionEntity transactionEntity = TransactionEntity.builder()
                .Price(totalPrice)
                .ebookName(ebookEntity.getTitle())
                .amount(transaction.amount())
                .transactionDateTime(LocalDateTime.now())
                .build();

        return convertEntityToResponse(transactionEntity);
    }


    private TransactionResponse convertEntityToResponse(TransactionEntity transactionEntity) {
        return TransactionResponse.builder()
                .id(transactionEntity.getId())
                .ebookName(transactionEntity.getEbookName())
                .ebookId(transactionEntity.getEbookId())
                .amount(transactionEntity.getAmount())
                .price(transactionEntity.getPrice())
                .transactionDateTime(transactionEntity.getTransactionDateTime())
                .build();
    }
}
