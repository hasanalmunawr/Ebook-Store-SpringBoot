package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;

import java.util.List;

public interface CustomerService {

    List<EbookResponse> FilterEbookByPriceRange(double min, double max);

    List<EbookResponse> FilterEbookByPriceCheaper();

    List<EbookResponse> FilterEbookByPriceExpensive();

    List<EbookResponse> FilterEbookByTitle(String title);

    List<EbookResponse> FilterEbookByAuthor(String author);
}
