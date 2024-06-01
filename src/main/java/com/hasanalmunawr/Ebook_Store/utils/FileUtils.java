package com.hasanalmunawr.Ebook_Store.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static byte[] convertFileToByteArray(File file) throws IOException, IOException {
        Path pdfPath = Paths.get("/path/to/your/file.pdf");
        byte[] pdf = Files.readAllBytes(pdfPath);

        return pdf;
    }
}
