package com.example.htpt.mapper;

import com.example.htpt.entity.dto.UserDto;
import com.example.htpt.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    User mapToUser(UserDto userDto);
    UserDto mapToUserDto (User user);
}
