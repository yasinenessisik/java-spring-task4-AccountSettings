package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {

}
