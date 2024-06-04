package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.SearchResponse;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {

    private final EbookRepository ebookRepository;

    @Override
    public void saveEbookInformation(EbookRequest request, UserEntity currentUser) {
        EbookEntity ebookEntity = convertDtoToEbookEntity(request);
        ebookEntity.setAuthor(currentUser);

        ebookRepository.save(ebookEntity);
    }

    @Override
    public SearchResponse getEbookInformation(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        return convertEbookEntityToDto(ebookEntity);
    }


    private SearchResponse convertEbookEntityToDto(EbookEntity ebookEntity) {
        return SearchResponse.builder()
                .title(ebookEntity.getTitle())
                .isbn(ebookEntity.getIsbn())
                .description(ebookEntity.getDescription())
                .price(ebookEntity.getPrice())
                .category(ebookEntity.getCategory())
                .language(ebookEntity.getLanguage())
                .publisher(ebookEntity.getPublisher())
                .publicationDate(ebookEntity.getPublicationDate())
                .availability(ebookEntity.isAvailability())
                .numberOfPages(ebookEntity.getNumberOfPages())
                .rating(ebookEntity.getRating())
                .build();

    }

    private EbookEntity convertDtoToEbookEntity(EbookRequest dto) {
        return EbookEntity.builder()
                .title(dto.getTitle())
                .isbn(dto.getIsbn())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .language(dto.getLanguage())
                .publicationDate(LocalDate.now())
                .availability(true)
                .numberOfPages(dto.getNumberOfPages())
                .publisher(dto.getPublisher())
                .rating(0)
                .build();
    }
}
