package com.alexeysmoliagin.springboot.sportclub.mapper.users;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.repository.users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.users.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsersMapper {
    UsersDto toDto(Users users);
    UsersDto toDto(UsersAddRequestModel users);
    UsersDto toDto(UsersUpdateRequestModel users);
    UsersDto toDto(UsersResponseModel users);
    Users toUsers(UsersDto users);
    UsersResponseModel toResponseModel (UsersDto usersDto);
    List <UsersDto> toUsersDtoList(List <Users> listUsers);
    List <UsersResponseModel> toUsersResponseModel(List <UsersDto> listDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "registerData", ignore = true)
    Users updateEntity(@MappingTarget Users user, UsersDto dto);
}
