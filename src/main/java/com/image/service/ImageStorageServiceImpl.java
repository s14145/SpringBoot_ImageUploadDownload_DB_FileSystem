package com.image.service;

import com.image.dto.ResponseMessage;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service("StorageServiceImpl")
@Slf4j
public class ImageStorageServiceImpl implements ImageStorageService {

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private ImageDataRepository imageDataRepository;

    private static final String FOLDER_PATH = "/Users/sudhilgauchan/Desktop/IntellijProjects/images/";

    public ResponseMessage uploadImage(MultipartFile multipartFile){
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
        ResponseMessage responseMessage = ResponseMessage.builder()
                .imageName(imageData.getName())
                .type(imageData.getType())
                .message("Successfully uploaded image to database.")
                .build();
        return responseMessage;
      }

      public byte[] downaloadImage(String fileName){
          Optional<ImageData> dbImage = imageDataRepository.findByName(fileName);
          if(!dbImage.isPresent()){
              throw new ImageNotFoundException("Image not found with: " + fileName + " in database.", HttpStatus.NOT_FOUND);
          }
          byte[] images = ImageUtils.decompressImage(dbImage.get().getImageData());
          return images;
      }

      public ResponseMessage uploadImageToFileSystem(MultipartFile multipartFile){
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
          ResponseMessage responseMessage = ResponseMessage.builder()
                  .imageName(fileData.getName())
                  .type(fileData.getType())
                  .filePath(filePath)
                  .message("Successfully uploaded image to file system.")
                  .build();
          return responseMessage;
      }

      public byte[] downloadImageFromFileSystem(String fileName) {
          Optional<FileData> fileSystemImage = fileDataRepository.findByName(fileName);
          if (!fileSystemImage.isPresent()) {
              throw new ImageNotFoundException("Image not found with " + fileName + " in file system.", HttpStatus.NOT_FOUND);
          }
          String filePath = fileSystemImage.get().getFilePath();
          byte[] images;
          try {
              images = Files.readAllBytes(new File(filePath).toPath());
          } catch (IOException e) {
              log.error("Error while reading image from file system: " + e.getMessage());
              throw new SystemException("Error while reading image from file system.", e);
          }
          return images;
      }

      public void deleteImageFromDB(String fileName){
          Optional<ImageData> dbImage = imageDataRepository.findByName(fileName);
          if(!dbImage.isPresent()){
              throw new ImageNotFoundException("Image not found with: " + fileName + " in database.", HttpStatus.NOT_FOUND);
          }
          ImageData imageData = dbImage.get();
          imageDataRepository.delete(imageData);
      }

      public void deleteImageFromFileSystem(String fileName){
          Optional<FileData> fileSystemImage = fileDataRepository.findByName(fileName);
          if(!fileSystemImage.isPresent()){
              throw new ImageNotFoundException("Image not found with " + fileName + " in file system.", HttpStatus.NOT_FOUND);
          }
          FileData fileData = fileSystemImage.get();
          fileDataRepository.delete(fileData);
          String filePath = fileSystemImage.get().getFilePath();
          Path fileToDeletePath = Paths.get(filePath);
          try {
              Files.delete(fileToDeletePath);
          } catch (IOException e) {
              log.error("Unable to delete image from file system: " + e.getMessage());
              throw new SystemException("Unable to delete image from file system.", e);
          }
      }
}