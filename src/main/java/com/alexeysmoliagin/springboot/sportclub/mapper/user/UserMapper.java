package com.alexeysmoliagin.springboot.sportclub.mapper.user;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UserUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.repository.user.entity.User;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);
    UserDto toDto(UserAddRequestModel user);
    UserDto toDto(UserUpdateRequestModel user);
    UserDto toDto(UserResponseModel user);
    User toUsers(UserDto user);
    UserResponseModel toResponseModel (UserDto userDto);
    List <UserDto> toUsersDtoList(List <User> listUsers);
    List <UserResponseModel> toUsersResponseModel(List <UserDto> listDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerData", ignore = true)
    User updateEntity(@MappingTarget User user, UserDto dto);
}
