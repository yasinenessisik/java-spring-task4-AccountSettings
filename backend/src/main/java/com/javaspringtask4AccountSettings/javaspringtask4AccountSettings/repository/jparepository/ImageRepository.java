package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageData,Integer> {
    Optional<ImageData> findByName(String name);
}
