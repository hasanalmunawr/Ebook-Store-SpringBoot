package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.dto.response.SearchResponse;
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
    public List<SearchResponse> FilterEbookByPriceRange(double min, double max) {
        List<EbookEntity> ebooks = ebookRepository.findByPriceRange(min, max);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }

        return convertEntityToDto(ebooks);
    }

    @Override
    public List<SearchResponse> FilterEbookByPriceCheaper() {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByPriceCheaper();
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<SearchResponse> FilterEbookByPriceExpensive() {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByPriceExpensive();
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<SearchResponse>  FilterEbookByTitle(String title) {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByTitle(title);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    @Override
    public List<SearchResponse>  FilterEbookByAuthor(String author) {
        List<EbookEntity> ebooks = ebookRepository.filterEbookByAuthor(author);
        if (ebooks.isEmpty()) {
            throw new EntityNotFoundException("No ebook found");
        }
        return convertEntityToDto(ebooks);
    }

    private List<SearchResponse> convertEntityToDto(List<EbookEntity> ebooks) {
        return ebooks.stream()
                .map(ebook -> {
                    SearchResponse searchResponse = new SearchResponse();

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
                    searchResponse.setCoverEbook(ebook.getCoverEbook());
                    searchResponse.setEbookFile(ebook.getEbookFile());
                    searchResponse.setAvailability(ebook.isAvailability());
                    return searchResponse;
                }).collect(Collectors.toList());
    }
}
