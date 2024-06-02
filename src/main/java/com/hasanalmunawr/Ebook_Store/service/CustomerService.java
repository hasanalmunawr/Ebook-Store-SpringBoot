package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.dto.response.SearchResponse;

import java.util.List;

public interface CustomerService {

    List<SearchResponse> FilterEbookByPriceRange(double min, double max);

    List<SearchResponse> FilterEbookByPriceCheaper();

    List<SearchResponse> FilterEbookByPriceExpensive();

    List<SearchResponse> FilterEbookByTitle(String title);

    List<SearchResponse> FilterEbookByAuthor(String author);
}
