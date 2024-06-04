package com.hasanalmunawr.Ebook_Store.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

//    void saveEbookFile(byte[] ebookFile, String isbn);
//
//    void saveEbookCover(byte[] ebookCover, String isbn);

    void saveEbookFile(MultipartFile file, String fileName, String isbn);
    void saveCoverFile(byte[] data, String fileName, String isbn);

    byte[] getEbookFile(String isbn);
    byte[] getCoverFile(String isbn);
}
