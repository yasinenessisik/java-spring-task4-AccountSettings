package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.redisrepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.UserRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserRedis,Integer> {
}
