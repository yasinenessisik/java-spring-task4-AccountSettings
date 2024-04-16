package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Integer> {
}
