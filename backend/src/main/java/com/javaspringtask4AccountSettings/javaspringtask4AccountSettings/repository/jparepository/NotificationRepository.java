package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.jparepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
