package com.image.service;

import com.image.entity.FileData;
import com.image.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    ImageData uploadImage(MultipartFile multipartFile) throws IOException;

    byte[] downaloadImage(String fileName);

    FileData uploadImageToFileSystem(MultipartFile multipartFile) throws IOException;

   byte[] downloadImageFromFileSystem(String fileName);
}
