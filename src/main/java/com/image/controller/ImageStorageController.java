package com.image.controller;


import com.image.entity.FileData;
import com.image.entity.ImageData;
import com.image.service.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/images")
public class ImageStorageController {

    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("/uploadToDB")
    public ResponseEntity<?> uploadImageToDB(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        ImageData uploadedImageToDB = imageStorageService.uploadImage(multipartFile);
        return new ResponseEntity<>(uploadedImageToDB, HttpStatus.CREATED);
    }

    @GetMapping(value = "/downloadFromDB/{fileName}", consumes = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> downloadImageFromDB(@PathVariable String fileName){
        byte[] downloadImageFromDB = imageStorageService.downaloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(downloadImageFromDB);
    }

    @PostMapping("/uploadToFileSystem")
    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        FileData uploadedImageToFileSystem = imageStorageService.uploadImageToFileSystem(multipartFile);
        return new ResponseEntity<>(uploadedImageToFileSystem, HttpStatus.CREATED);
    }

    @GetMapping(value = "/downloadFromFileSystem/{fileName}", consumes = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName){
        byte[] downloadImageFromFileSystem = imageStorageService.downloadImageFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/jpeg"))
                .body(downloadImageFromFileSystem);
    }

    @DeleteMapping("/deleteFromDB/{fileName}")
    public ResponseEntity<Void> deleteImageFromDB(@PathVariable String fileName){
        imageStorageService.deleteImageFromDB(fileName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/deleteFromFileSystem/{fileName}")
    public ResponseEntity<Void> deleteImageFromFileSystem(@PathVariable String fileName){
        imageStorageService.deleteImageFromFileSystem(fileName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}