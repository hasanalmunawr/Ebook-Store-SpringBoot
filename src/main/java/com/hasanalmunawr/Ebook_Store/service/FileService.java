package com.hasanalmunawr.Ebook_Store.service;

import com.hasanalmunawr.Ebook_Store.dto.response.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {

    void saveEbookFile(MultipartFile file, String isbn);

    void saveCoverFile(MultipartFile file, String isbn);

    FileResponse getEbookFile(String isbn);

    FileResponse getCoverFile(String isbn);

}
