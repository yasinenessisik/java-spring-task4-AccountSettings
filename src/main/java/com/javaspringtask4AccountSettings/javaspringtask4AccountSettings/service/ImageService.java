package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.config.StorageConfig;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.ImageData;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
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
        String filePath=fileData.get().getImagePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public String updateImageInFileSystem(String fileName, MultipartFile newFile) throws IOException {
        Optional<ImageData> fileData = imageRepository.findByName(fileName);
        if (fileData.isPresent()) {
            String filePath = fileData.get().getImagePath();
            File oldFile = new File(filePath);
            if (oldFile.exists()) {
                oldFile.delete();
                newFile.transferTo(new File(filePath));
                return "file updated successfully : " + fileName;
            } else {
                return "file does not exist : " + fileName;
            }
        } else {
            return "file not found : " + fileName;
        }
    }
    private String getFileExtension(String filename) {
        if (filename != null && filename.lastIndexOf(".") != -1) {
            return filename.substring(filename.lastIndexOf("."));
        }
        return "";
    }
}
