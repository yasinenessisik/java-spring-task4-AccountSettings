package com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.converter;

import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.dto.BaseEntityDto;
import com.javaspringtask4AccountSettings.javaspringtask4AccountSettings.model.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class BaseEntityDtoConverter {

    public BaseEntityDto convert(BaseEntity from){
        return BaseEntityDto.builder()
                .createdDate(from.getCreatedDate())
                .modifiedDate(from.getModifiedDate())
                .build();
    }
}
