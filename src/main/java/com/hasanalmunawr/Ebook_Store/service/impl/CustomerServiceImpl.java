package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.response.EbookResponse;
import com.hasanalmunawr.Ebook_Store.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final EbookRepository ebookRepository;

    @Override
    public List<EbookResponse> FilterEbookByPriceRange(double min, double max) {
        List<EbookEntity> ebooks = ebookRepository.findByPriceRange(min, max);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }

        return convertEntityToDto(ebooks);
    }

    @Override
    public List<EbookResponse> FilterEbookByPriceCheaper() {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByPriceCheaper();
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<EbookResponse> FilterEbookByPriceExpensive() {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByPriceExpensive();
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<EbookResponse>  FilterEbookByTitle(String title) {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByTitle(title);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<EbookResponse>  FilterEbookByAuthor(String author) {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByAuthor(author);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    private List<EbookResponse> convertEntityToDto(List<EbookEntity> ebooks) {
        return ebooks.stream()
                .map(ebook -> {
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
                    searchResponse.setRating(ebook.getRating());
                    searchResponse.setAvailability(ebook.isAvailability());
                    return searchResponse;
                }).collect(Collectors.toList());
    }
}
