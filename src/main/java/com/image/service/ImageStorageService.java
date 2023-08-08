package com.image.service;

import com.image.dto.ResponseMessage;
import com.image.entity.FileData;
import com.image.entity.ImageData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageStorageService {

    ResponseMessage uploadImage(MultipartFile multipartFile) throws IOException;

    byte[] downaloadImage(String fileName);

    ResponseMessage uploadImageToFileSystem(MultipartFile multipartFile) throws IOException;

   byte[] downloadImageFromFileSystem(String fileName);

    void deleteImageFromDB(String fileName);

    void deleteImageFromFileSystem(String fileName);
}