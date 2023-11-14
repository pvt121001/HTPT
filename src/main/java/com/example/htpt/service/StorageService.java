package com.example.htpt.service;

import com.example.htpt.exception.StorageException;
import com.example.htpt.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    String getStoredFileName(MultipartFile file, String id);

    void store(MultipartFile file, String storedFileName) throws StorageException;

    Resource loadAsRecource(String filename) throws StorageFileNotFoundException;

    Path load(String filename);

    void delete(String storeFilename) throws IOException;

    void init() throws StorageException;
}
