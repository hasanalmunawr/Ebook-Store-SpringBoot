package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.utils.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.hasanalmunawr.Ebook_Store.utils.FileUtils.compressFile;
import static com.hasanalmunawr.Ebook_Store.utils.FileUtils.decompressFile;

@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final EbookRepository ebookRepository;

    @Override
    public void saveEbookFile(byte[] ebookFile, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        ebookEntity.setEbookFile(compressFile(ebookFile));
        ebookRepository.save(ebookEntity);
    }

    @Override
    public void saveEbookCover(byte[] ebookCover, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        ebookEntity.setCoverEbook(compressFile(ebookCover));
        ebookRepository.save(ebookEntity);
    }

    @Override
    @Transactional
    public byte[] downloadFile(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        return decompressFile(ebookEntity.getEbookFile());
    }

    @Override
    public byte[] seeCover(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        return decompressFile(ebookEntity.getEbookFile());
    }
}
