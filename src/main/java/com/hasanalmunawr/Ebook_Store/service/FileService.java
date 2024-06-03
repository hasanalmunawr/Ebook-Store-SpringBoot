package com.hasanalmunawr.Ebook_Store.service;

import java.io.File;

public interface FileService {

    void saveEbookFile(byte[] ebookFile, String isbn);

    void saveEbookCover(byte[] ebookCover, String isbn);

    byte[] downloadFile(String isbn);

    byte[] seeCover(String isbn);
}
