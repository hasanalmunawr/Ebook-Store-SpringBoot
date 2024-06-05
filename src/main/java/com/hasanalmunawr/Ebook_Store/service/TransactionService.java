package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.TransactionRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.TransactionResponse;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;

public interface TransactionService {

    TransactionResponse createTransaction(TransactionRequest transaction, UserEntity user);
}
