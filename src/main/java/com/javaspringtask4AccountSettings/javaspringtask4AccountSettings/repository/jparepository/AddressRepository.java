package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
