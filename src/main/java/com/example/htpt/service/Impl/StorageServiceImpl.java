package com.example.htpt.service.Impl;

import com.example.htpt.config.StorageProperties;
import com.example.htpt.exception.StorageException;
import com.example.htpt.exception.StorageFileNotFoundException;
import com.example.htpt.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;


@Service
public class StorageServiceImpl implements StorageService {
    public final Path rootLocation;// duonwgf dẫn gốc lưu hình ảnh

    @Override
    public String getStoredFileName(MultipartFile file, String id) {
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());// trả tên fileupload đến server
        return "p" + id + "." + ext;
    }

    //cấu hình lưu trữ
    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }

    //lưu

    @Override
    public void store(MultipartFile file, String storedFileName) throws StorageException {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storedFileName))
                    .normalize().toAbsolutePath();// tính toán , lấy thông tin, chuẩn hóa, chuyển đường dẫn thành tuyệt dối
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new StorageException("Can not store file outside current directory");
            }

            try(InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new StorageException("Failed to store file", e);
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }


    // nạp tài nguyên
    @Override
    public Resource loadAsRecource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            // nếu tồn tại và cập nhật được
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }

            throw new StorageFileNotFoundException("Could not read file: " + filename);
        } catch (Exception e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void delete(String storeFilename) throws IOException {
        Path destinationFile = rootLocation.resolve(Paths.get(storeFilename))
                .normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }


    //khởi tạo
    @Override
    public void init() throws StorageException {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new StorageException("Could not init storage", e);
        }
    }


}

