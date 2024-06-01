package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EbookServiceImpl implements EbookService {

    private final EbookRepository bookRepository;
    @Override
    public void saveEbookInformation(EbookRequest request, UserEntity currentUser) {
        EbookEntity ebookEntity = convertDtoToEbookEntity(request);
        ebookEntity.setAuthor(currentUser);

        bookRepository.save(ebookEntity);
    }

    private EbookEntity convertDtoToEbookEntity(EbookRequest dto) {
        EbookEntity ebookEntity =  EbookEntity.builder()
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

        return ebookEntity;
    }
}
