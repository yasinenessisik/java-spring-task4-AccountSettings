package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ImageRepository extends JpaRepository<ImageData,Integer> {
    Optional<ImageData> findByName(String name);
}
