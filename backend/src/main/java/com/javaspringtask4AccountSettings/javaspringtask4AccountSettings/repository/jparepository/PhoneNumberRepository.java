package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Integer> {
}
