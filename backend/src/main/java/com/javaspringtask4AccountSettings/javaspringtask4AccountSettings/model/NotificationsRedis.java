package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.repository.CrudRepository;
@RedisHash("Notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationsRedis{
    @Id
    private Integer notificationId;

    private Boolean accountBalanceUpdate;

    private Boolean transcationUpdate;

    private Boolean securityAlert;

}
