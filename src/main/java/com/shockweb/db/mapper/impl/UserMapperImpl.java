package com.shockweb.db.mapper.impl;

import com.shockweb.db.domain.dto.UserDto;
import com.shockweb.db.domain.entity.UserEntity;
import com.shockweb.db.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements Mapper<UserEntity, UserDto> {

    private ModelMapper modelMapper;

    public UserMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserEntity toEntity(UserDto userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public void mapNonNullFields(UserEntity updatedUserEntity, UserEntity userEntity) {
        if (updatedUserEntity.getFirstName() != null) {
            userEntity.setFirstName(updatedUserEntity.getFirstName());
        }
        if (updatedUserEntity.getLastName() != null) {
            userEntity.setLastName(updatedUserEntity.getLastName());
        }
        if (updatedUserEntity.getPhone() != 0) {
            userEntity.setPhone(updatedUserEntity.getPhone());
        }
        if (updatedUserEntity.getPassword() != null) {
            userEntity.setPassword(updatedUserEntity.getPassword());
        }
    }
}




