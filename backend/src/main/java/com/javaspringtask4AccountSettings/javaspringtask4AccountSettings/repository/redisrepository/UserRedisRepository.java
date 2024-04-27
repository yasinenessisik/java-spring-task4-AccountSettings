package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.repository.redisrepository;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedisRepository extends CrudRepository<UserDto,String> {
}
