package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
}