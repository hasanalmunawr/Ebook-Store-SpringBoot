package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    EbookResponse searchEbookByIsbn(String isbn);

    Page<EbookResponse> FilterEbookByPriceRange(double min, double max, int page, int size);

    Page<EbookResponse> FilterEbookByPriceCheaper(int page, int size);

    Page<EbookResponse> FilterEbookByPriceExpensive(int page, int size);

    Page<EbookResponse> FilterEbookByTitle(String title, int page, int size);

    Page<EbookResponse> FilterEbookByAuthor(String author, int page, int size);

    Page<EbookResponse> FilterEbookByPublisher(String publisher, int page, int size);

    Page<EbookResponse> filterBook(double min, double max, String sort, int page, int size);
}
