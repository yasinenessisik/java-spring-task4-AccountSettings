package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.controller;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }
    @PostMapping("/fileSystem/{profilePhotoPath}")
    public ResponseEntity<?> uploadImageToFIleSystem(@PathVariable String profilePhotoPath,@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = service.uploadImageToFileSystem(profilePhotoPath,file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/fileSystem/{profilePhotoPath}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String profilePhotoPath) throws IOException {
        byte[] imageData=service.downloadImageFromFileSystem(profilePhotoPath);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    @PutMapping("/fileSystem/{profilePhotoPath}")
    public ResponseEntity<?> updateImageInFileSystem(@PathVariable String profilePhotoPath, @RequestParam("image") MultipartFile newFile) throws IOException {
        String updateResult = service.updateImageInFileSystem(profilePhotoPath, newFile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(updateResult);
    }
}
