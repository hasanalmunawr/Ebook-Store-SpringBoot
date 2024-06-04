package com.hasanalmunawr.Ebook_Store.service.impl;

import com.hasanalmunawr.Ebook_Store.book.EbookEntity;
import com.hasanalmunawr.Ebook_Store.book.EbookRepository;
import com.hasanalmunawr.Ebook_Store.service.FileService;
import com.hasanalmunawr.Ebook_Store.utils.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;

import static com.hasanalmunawr.Ebook_Store.utils.FileUtils.compressFile;
import static com.hasanalmunawr.Ebook_Store.utils.FileUtils.decompressFile;

@Service
@RequiredArgsConstructor
@Transactional
public class FileServiceImpl implements FileService {

    private final EbookRepository ebookRepository;

    @Override
    public void saveEbookFile(MultipartFile file, String fileName, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        Path pathSave = Path.of("files/" + fileName);
        try {
            file.transferTo(pathSave);
            ebookEntity.setEbookFile(pathSave.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void saveCoverFile(byte[] data, String coverName, String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        Path pathSave = Path.of("src/main/resources/images");
        try (OutputStream outputStream = new FileOutputStream(pathSave + "\\" + coverName)) {
            outputStream.write(data);

            ebookEntity.setCoverEbook(outputStream.toString());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public byte[] getCoverFile(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
        try {
            InputStream inputStream = new FileInputStream(ebookEntity.getCoverEbook());
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getEbookFile(String isbn) {
        var ebookEntity = ebookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));

        try {
            InputStream inputStream = new FileInputStream(ebookEntity.getEbookFile());
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public void saveEbookFile(byte[] ebookFile, String isbn) {
//        var ebookEntity = ebookRepository.findByIsbn(isbn)
//                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
//        ebookEntity.setEbookFile(compressFile(ebookFile));
//        ebookRepository.save(ebookEntity);
//    }
//
//    @Override
//    public void saveEbookCover(byte[] ebookCover, String isbn) {
//        var ebookEntity = ebookRepository.findByIsbn(isbn)
//                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
//        ebookEntity.setCoverEbook(compressFile(ebookCover));
//        ebookRepository.save(ebookEntity);
//    }
//
//    @Override
//    @Transactional
//    public byte[] downloadFile(String isbn) {
//        var ebookEntity = ebookRepository.findByIsbn(isbn)
//                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
//
//        return decompressFile(ebookEntity.getEbookFile());
//    }
//
//    @Override
//    public byte[] seeCover(String isbn) {
//        var ebookEntity = ebookRepository.findByIsbn(isbn)
//                .orElseThrow(() -> new EntityNotFoundException("Ebook Not found"));
//
//        return decompressFile(ebookEntity.getEbookFile());
//    }
}
