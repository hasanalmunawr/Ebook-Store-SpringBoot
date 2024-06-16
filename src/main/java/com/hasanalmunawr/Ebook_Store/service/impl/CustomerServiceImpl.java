package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final EbookRepository ebookRepository;

    @Override
    public EbookResponse searchEbookByIsbn(String isbn) {
        EbookEntity ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook not found"));

        return convertDto(ebookEntity);
    }

    @Override
    public Page<EbookResponse> FilterEbookByPriceRange(double min, double max, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        Page<EbookEntity> ebooks = ebookRepository.findByPriceRange(min, max, pageRequest);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }

        return convertEntityToDtoPage(ebooks);
    }

    @Override
    public Page<EbookResponse> FilterEbookByPriceCheaper(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<EbookEntity> ebooks = ebookRepository.filterEbookByPriceCheaper(pageRequest);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(ebooks);
    }

    @Override
    public Page<EbookResponse> FilterEbookByPriceExpensive(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<EbookEntity> ebooks = ebookRepository.filterEbookByPriceExpensive(pageRequest);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(ebooks);
    }


    @Override
    public Page<EbookResponse>  FilterEbookByTitle(String title, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<EbookEntity> ebooks = ebookRepository.filterEbookByTitle(title, pageRequest);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(ebooks);
    }

    @Override
    public Page<EbookResponse>  FilterEbookByAuthor(String author, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<EbookEntity> ebooks = ebookRepository.filterEbookByAuthor(author, pageRequest);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(ebooks);
    }

    @Override
    public Page<EbookResponse> FilterEbookByPublisher(String publisher, int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<EbookEntity> ebooks = ebookRepository.findByPublisher(publisher, pageRequest);

        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(ebooks);
    }

    @Override
    public Page<EbookResponse> filterBook(double min, double max, String sort, int page, int size) {
        Pageable pageable = null;
        if (sort.equals("asc")) {
            pageable = PageRequest.of(page, size, Sort.by("price").ascending());
        } else if (sort.equals("dsc")) {
            pageable = PageRequest.of(page, size, Sort.by("price").descending());
        }
        Page<EbookEntity> byPriceBetween = ebookRepository.findByPriceBetween(min, max, pageable);

        if (byPriceBetween.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDtoPage(byPriceBetween);
    }

    private Page<EbookResponse> convertEntityToDtoPage(Page<EbookEntity> ebooks) {
        return ebooks.map(ebook -> {
            EbookResponse searchResponse = new EbookResponse();

            searchResponse.setTitle(ebook.getTitle());
            searchResponse.setIsbn(ebook.getIsbn());
            searchResponse.setPublisher(ebook.getPublisher());
            searchResponse.setCategory(ebook.getCategory());
            searchResponse.setDescription(ebook.getDescription());
            searchResponse.setPrice(ebook.getPrice());
            searchResponse.setLanguage(ebook.getLanguage());
            searchResponse.setPublicationDate(ebook.getPublicationDate());
            searchResponse.setNumberOfPages(ebook.getNumberOfPages());
            searchResponse.setRating(ebook.getRating());
            searchResponse.setAvailability(ebook.isAvailability());

            return searchResponse;
        });
    }

    private EbookResponse convertDto(EbookEntity ebook) {
        var searchResponse = new EbookResponse();
        searchResponse.setTitle(ebook.getTitle());
        searchResponse.setIsbn(ebook.getIsbn());
        searchResponse.setPublisher(ebook.getPublisher());
        searchResponse.setCategory(ebook.getCategory());
        searchResponse.setDescription(ebook.getDescription());
        searchResponse.setPrice(ebook.getPrice());
        searchResponse.setLanguage(ebook.getLanguage());
        searchResponse.setPublicationDate(ebook.getPublicationDate());
        searchResponse.setNumberOfPages(ebook.getNumberOfPages());
        searchResponse.setRating(ebook.getRating());
        searchResponse.setRating(ebook.getRating());
        searchResponse.setAvailability(ebook.isAvailability());

        return searchResponse;
    }
}
