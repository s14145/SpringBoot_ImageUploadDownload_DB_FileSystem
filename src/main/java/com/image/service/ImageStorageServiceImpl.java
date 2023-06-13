package com.image.service;

import com.image.entity.FileData;
import com.image.entity.ImageData;
import com.image.exception.ImageNotFoundException;
import com.image.exception.SystemException;
import com.image.repository.FileDataRepository;
import com.image.repository.ImageDataRepository;
import com.image.utils.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service("StorageServiceImpl")
@Slf4j
public class ImageStorageServiceImpl implements ImageStorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private ImageDataRepository imageDataRepository;

    private static final String FOLDER_PATH = "/Users/sudhilgauchan/Desktop/IntellijProjects/images/";

    public ImageData uploadImage(MultipartFile multipartFile){
      if(multipartFile.isEmpty()){
          throw new RuntimeException("No File selected");
      }
        ImageData imageData = null;
        try {
            imageData = imageDataRepository.save(ImageData.builder()
                            .name(multipartFile.getOriginalFilename())
                            .type(multipartFile.getContentType())
                            .imageData(ImageUtils.compressImage(multipartFile.getBytes()))
                            .build());
        } catch (IOException e) {
            log.error("Unable to upload image to database: " + e.getMessage());
            throw new SystemException("Unable to upload image to database.", e);
        }
        return imageData;
      }

      public byte[] downaloadImage(String fileName){
          Optional<ImageData> dbImageData = imageDataRepository.findByName(fileName);
          if(!dbImageData.isPresent()){
              throw new ImageNotFoundException("Image not found with: " + fileName + " in database.", HttpStatus.NOT_FOUND);
          }
          byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
          return images;
      }

      public FileData uploadImageToFileSystem(MultipartFile multipartFile){
        if(multipartFile.isEmpty()){
              throw new RuntimeException("No File selected");
          }
        String filePath = FOLDER_PATH + multipartFile.getOriginalFilename();
        FileData fileData = fileDataRepository.save(FileData.builder()
                       .name(multipartFile.getOriginalFilename())
                       .type(multipartFile.getContentType())
                       .filePath(filePath)
                       .build());
          try {
              multipartFile.transferTo(new File(filePath));
          } catch (IOException e) {
              log.error("Unable to upload image to file system: " + e.getMessage());
              throw new SystemException("Unable to upload image to file system.", e);
          }
          return fileData;
      }

      public byte[] downloadImageFromFileSystem(String fileName) {
          Optional<FileData> fileSystemFileData = fileDataRepository.findByName(fileName);
          if (!fileSystemFileData.isPresent()) {
              throw new ImageNotFoundException("Image not found with " + fileName + " in file system.", HttpStatus.NOT_FOUND);
          }
          String filePath = fileSystemFileData.get().getFilePath();
          byte[] images;
          try {
              images = Files.readAllBytes(new File(filePath).toPath());
          } catch (IOException e) {
              log.error("Error while reading image from file system" + e.getMessage());
              throw new SystemException("Error while reading image from file system.", e);
          }
          return images;
      }
}