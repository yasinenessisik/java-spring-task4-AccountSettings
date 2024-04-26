package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("user")
@Data
@AllArgsConstructor
public class UserRedis {
    @Id
    private String userId;

    private String firstName;

    @TimeToLive
    private Long timeToLive=30L;

    private NotificationsRedis notificationsRedis;
}
