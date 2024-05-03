package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.config.StorageConfig;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.request.image.ImageRequestDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.ErrorCode;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.exception.GenericExceptionHandler;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.ImageData;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Optional;

@Service
@Slf4j
public class ImageService {


    private final ImageRepository imageRepository;
    private final StorageConfig storageConfig;
    private final UserService userService;

    public ImageService(ImageRepository imageRepository, StorageConfig storageConfig, UserService userService) {
        this.imageRepository = imageRepository;
        this.storageConfig = storageConfig;
        this.userService = userService;
    }


    public String uploadImageToFileSystem(String userId, MultipartFile file) throws IOException {
        User user = userService.getUserByUserId(userId);
        String fileName = user.getProfilePhotoPath().trim();
        String filePath = storageConfig.folderPath() + File.separator + fileName;
        ImageData fileData = imageRepository.save(ImageData.builder()
                .name(fileName)
                .type(file.getContentType())
                .imagePath(filePath).build());

        File directory = new File(storageConfig.folderPath());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully: " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String userId) throws IOException {
        User user = userService.getUserByUserId(userId);
        Optional<ImageData> fileData = imageRepository.findByName(user.getProfilePhotoPath());
        if (!fileData.isPresent()) {
            throw GenericExceptionHandler.builder()
                    .errorMessage("File Not Found.")
                    .errorCode(ErrorCode.FILE_NOT_FOUND)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .build();
        }

        String filePath=fileData.get().getImagePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    @Transactional
    public String updateImageInFileSystem(ImageRequestDto imageRequestDto) {
        try {
            User user = userService.getUserByUserId(imageRequestDto.getUserId());
            String userId = imageRequestDto.getUserId();
            Optional<ImageData> fileData = imageRepository.findByName(user.getProfilePhotoPath());
            byte[] imageData = Base64.getDecoder().decode(imageRequestDto.getImageBase64());

            if (fileData.isPresent()) {
                String filePath = fileData.get().getImagePath();
                File oldFile = new File(filePath);
                if (oldFile.exists()) {
                    if (oldFile.delete()) {
                        Files.write(Paths.get(storageConfig.folderPath(), fileData.get().getName()), imageData, StandardOpenOption.CREATE);
                        return "File updated successfully: " + userId;
                    } else {
                        return "Failed to delete existing file: " + userId;
                    }
                } else {
                    Files.write(Paths.get(storageConfig.folderPath(), fileData.get().getName()), imageData, StandardOpenOption.CREATE);
                    return "New file created: " + userId;
                }
            } else {
                String fileName = user.getProfilePhotoPath().trim();
                // Assuming imagePath should be the same as the folder path
                String imagePath = Paths.get(storageConfig.folderPath(), fileName).toString();
                imageRepository.save(ImageData.builder()
                        .name(fileName)
                        .type("image/jpeg")
                        .imagePath(imagePath).build());
                Files.write(Paths.get(storageConfig.folderPath(), fileName), imageData, StandardOpenOption.CREATE);
                return "File not found: " + userId;
            }
        } catch (IOException e) {
            // Log the exception for better error tracing
            log.error("Failed to update file: " + e.getMessage(), e);
            return "Failed to update file: " + e.getMessage();
        }
    }


}
