package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
}
