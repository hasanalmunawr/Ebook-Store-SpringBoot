package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.EbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.UpdateEbookRequest;
import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.service.EbookService;
import com.hasanalmunawr.Ebook_Store.user.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public EbookResponse getEbookInformation(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        return convertEbookEntityToDto(ebookEntity);
    }

    @Override
    @Transactional
    public EbookResponse updateEbookInformation(UpdateEbookRequest request, String isbn, UserEntity currentUser) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        try {
            ebookEntity.setPublisher(request.getPublisher());
            ebookEntity.setCategory(request.getCategory());
            ebookEntity.setDescription(request.getDescription());
            ebookEntity.setPrice(request.getPrice());
            ebookEntity.setLanguage(request.getLanguage());
            ebookEntity.setNumberOfPages(request.getNumberOfPages());
            ebookEntity.setAvailability(request.isAvailable());
            ebookRepository.save(ebookEntity);

            return convertEbookEntityToDto(ebookEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteEbook(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        try {
            ebookRepository.delete(ebookEntity);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

    }


    private EbookResponse convertEbookEntityToDto(EbookEntity ebookEntity) {
        return EbookResponse.builder()
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
