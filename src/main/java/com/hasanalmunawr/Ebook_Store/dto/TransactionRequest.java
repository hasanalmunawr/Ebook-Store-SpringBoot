package com.hasanalmunawr.Ebook_Store.dto;

public record TransactionRequest(
        String ebookIsbn,
        Integer amount) {


}
