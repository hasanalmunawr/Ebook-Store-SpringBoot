package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final EbookRepository ebookRepository;

    @Override
    public void saveEbookFile(byte[] ebookFile, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        ebookEntity.setEbookFile(ebookFile);
        ebookRepository.save(ebookEntity);
    }

    @Override
    public void saveEbookCover(byte[] ebookCover, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        ebookEntity.setCoverEbook(ebookCover);
        ebookRepository.save(ebookEntity);
    }
}
