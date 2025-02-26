package com.alexeysmoliagin.springboot.sportclub.mapper.usersMapper;

import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersAddRequestModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersResponseModel;
import com.alexeysmoliagin.springboot.sportclub.controller.users.model.UsersUpdateRequestModel;
import com.alexeysmoliagin.springboot.sportclub.repository.Users.entity.Users;
import com.alexeysmoliagin.springboot.sportclub.service.dto.UsersDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsersMapper {

    UsersDto toDto(Users users);
    UsersDto toDto(UsersAddRequestModel users);
    UsersDto toDto(UsersUpdateRequestModel users);
    UsersDto toDto(UsersResponseModel users);
    Users toUsers(UsersDto users);
    UsersResponseModel toResponseModel (UsersDto usersDto);
    List <UsersDto> toUsersDtoList(List <?> listUsers);
    List <UsersResponseModel> toUsersResponseModel(List <UsersDto> listDto);
}
